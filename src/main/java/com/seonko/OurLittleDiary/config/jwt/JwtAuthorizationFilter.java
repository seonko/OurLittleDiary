package com.seonko.OurLittleDiary.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    // 액세스 토큰 재발급
    protected String reIssueAccessToken(Member member) {
        String accessToken = null;

        accessToken = JWT.create()
                .withSubject(member.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("id", member.getId())
                .withClaim("username", member.getEmail())
                .withClaim("nickname", member.getNickname())
                .sign(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET));
        System.out.println("ACCESS 토큰 재발급");
        return accessToken;
    }

    // 리프레시 토큰 재발급
    protected String reIssueRefreshToken(Member member) {
        String refreshToken = null;

        refreshToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME))
                .withClaim("id", member.getId())
                .withClaim("username", member.getEmail())
                .withClaim("nickname", member.getNickname())
                .sign(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET));
        System.out.println("REFRESH 토큰 재발급");
        return refreshToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);
        String c = request.getHeader("Cookie");
        String[] cookies = null;

        String refreshToken = null;
        String accessToken = null;
        String accessUsername = null;
        String refreshUsername = null;
        Boolean isAccessTokenValidate = true;
        Boolean isRefreshTokenValidate = true;
        Member member = null;

        // 액세스 토큰 검증
        try {
            if (header != null) {
                accessToken = header.replace(JwtProperties.TOKEN_PREFIX, "");
                accessUsername = JWT.require(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET)).build().verify(accessToken)
                        .getClaim("username").asString();
            } else {
                chain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            isAccessTokenValidate = false;
        }

        // 리프레시 토큰 검증
        try {
            if (c != null) {
                cookies = c.split("; ");
                for (String cookie : cookies) {
                    String[] tmp = cookie.split("=");
                    if (tmp[0].equals("refresh_token")) {
                        refreshToken = tmp[1].replace(JwtProperties.TOKEN_PREFIX, "");
                    }
                    refreshUsername = JWT.require(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET)).build().verify(refreshToken)
                            .getClaim("username").asString();
                }
            } else{
                chain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            isRefreshTokenValidate = false;
        }

        if (isRefreshTokenValidate && isAccessTokenValidate) {
            member = memberRepository.findByEmail(accessUsername).orElse(null);
        } else if (!isAccessTokenValidate) {
            if (isRefreshTokenValidate) {
                member = memberRepository.findByEmail(refreshUsername).orElse(null);
                accessToken = reIssueAccessToken(member);
                response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
            } else {
                // 재로그인 필요
                response.addHeader(JwtProperties.HEADER_STRING, "Login Invalidate");
                chain.doFilter(request, response);
                return;
            }
        } else {
            member = memberRepository.findByEmail(accessUsername).orElse(null);
            refreshToken = reIssueRefreshToken(member);
            response.addHeader("Set-Cookie", "refresh_token=" + JwtProperties.TOKEN_PREFIX + refreshToken + "; HttpOnly");
        }

        if (member != null) {
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}