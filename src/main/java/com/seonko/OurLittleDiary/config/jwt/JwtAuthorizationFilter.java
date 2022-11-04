package com.seonko.OurLittleDiary.config.jwt;

import antlr.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
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

// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    // 액세스 토큰 재발급
    protected String reIssueAccessToken(String refreshToken) {
        String accessToken = null;

        String username = JWT.require(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET)).build().verify(refreshToken)
                .getClaim("username").asString();
        Member member = memberRepository.findByEmail(username).orElse(null);
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

    protected String reIssueRefreshToken(String accessToken) {
        String refreshToken = null;

        String username = JWT.require(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET)).build().verify(accessToken)
                .getClaim("username").asString();
        Member member = memberRepository.findByEmail(username).orElse(null);
        refreshToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME))
                .withClaim("id", member.getId())
                .withClaim("username", member.getEmail())
                .withClaim("nickname", member.getNickname())
                .sign(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET));
        return refreshToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);
        String c = request.getHeader("Cookie");
        Boolean isAccessTokenValidate = true;
        Boolean isRefreshTokenValidate = true;

        // 리프레시 토큰 유효 체크
        String[] cookies = null;
        String rtk = null;

        // 쿠키에서 리프레시 토큰 확인
        if (c != null) {
            cookies = c.split("; ");

            for (String cookie : cookies) {
                String[] tmp = cookie.split("=");
                if (tmp[0].equals("rtk")) {
                    rtk = tmp[1].replace(JwtProperties.TOKEN_PREFIX, "");
                }
            }
        }

        String username = null;
        Member member = null;

        try {
            username = JWT.require(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET)).build().verify(rtk)
                    .getClaim("username").asString();
        } catch (TokenExpiredException e) {
            isRefreshTokenValidate = false;
        } catch (JWTDecodeException e) {
            isRefreshTokenValidate = false;
        }


//        if (header == null) { // access 토큰 없음
//            if (rtk != null) { // 리프레시 토큰 있으면 액세스 토큰 재발급
//                String accessToken = reIssueAccessToken(rtk);
//                response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
//            }
//            chain.doFilter(request, response);
//            return;
//        } else if (!header.startsWith(JwtProperties.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }

        if (header != null && !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

//        // 토큰 검증
//        try {
//            String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
//            username = JWT.require(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET)).build().verify(token)
//                    .getClaim("username").asString();
//            member = memberRepository.findByEmail(username).orElse(null);
//        } catch (TokenExpiredException e) { // access 토큰 기한 만료!
//            System.out.println("액세스 토큰 기한 만료");
//            if (rtk != null) { // 리프레시 토큰 있으면
//                // 액세스 토큰 재발급
//                username = JWT.require(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET)).build().verify(rtk)
//                        .getClaim("username").asString();
//                member = memberRepository.findByEmail(username).orElse(null);
//                String accessToken = JWT.create()
//                        .withSubject(member.getEmail())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
//                        .withClaim("id", member.getId())
//                        .withClaim("username", member.getEmail())
//                        .withClaim("nickname", member.getNickname())
//                        .sign(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET));
//                System.out.println("ACCESS 토큰 재발급");
//                response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
//            }
//        }

        String token = null;

        try {
            token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
            username = JWT.require(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET)).build().verify(token)
                    .getClaim("username").asString();
            member = memberRepository.findByEmail(username).orElse(null);
        } catch (TokenExpiredException e) { // access 토큰 기한 만료!
            isAccessTokenValidate = false;
        } catch (JWTDecodeException e) {
            isAccessTokenValidate = false;
        } catch (NullPointerException e) {
            isAccessTokenValidate = false;
        }

        if (!isRefreshTokenValidate && isAccessTokenValidate) {
            String refreshToken = reIssueRefreshToken(token);
            response.addHeader("Set-Cookie", "rtk=" + JwtProperties.TOKEN_PREFIX + refreshToken + "; HttpOnly");
            System.out.println("Refresh Token 재발급");
        } else if (isRefreshTokenValidate && !isAccessTokenValidate) {
            String accessToken = reIssueAccessToken(rtk);
            response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
            System.out.println("Access Token 재발급");
        } else if (!isRefreshTokenValidate && !isAccessTokenValidate) {
            response.addHeader("LoginInvalidate", "true");
        }

        if (member != null) {
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
