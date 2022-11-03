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

// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);
        String c = request.getHeader("Cookie");
        String[] cookies = null;

        if (c != null) {
            cookies = c.split("; ");
        }

        System.out.println("header " + header);
        System.out.println("cookie " + cookies);
        String rtk = null;
        String username = null;
        Member member = null;

//        if (header == null) {
//            if (cookies != null && cookies.length > 0) { // cookie 확인하여 refresh token 확인
//                for (String cookie : cookies) {
//                    String[] tmp = cookie.split("=");
//                    if (tmp[0].equals("rtk")) {
//                        rtk = tmp[1].replace(JwtProperties.TOKEN_PREFIX, "");
//                    }
//                }
//            }
//            System.out.println("rtk" + rtk);
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
//            if (member != null) {
//                PrincipalDetails principalDetails = new PrincipalDetails(member);
//                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//            chain.doFilter(request, response);
//            return;
//        }
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");



        // 토큰 검증
        try {
            username = JWT.require(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET)).build().verify(token)
                    .getClaim("username").asString();
            member = memberRepository.findByEmail(username).orElse(null);
        } catch (TokenExpiredException e) { // access 토큰 기한 만료!
            System.out.println("토큰 기한 만료");
            if (cookies != null && cookies.length > 0) { // cookie 확인하여 refresh token 확인
                for (String cookie : cookies) {
                    String[] tmp = cookie.split("=");
                    if (tmp[0].equals("rtk")) {
                        rtk = tmp[1].replace(JwtProperties.TOKEN_PREFIX, "");
                    }
                }
            }
            System.out.println("rtk" + rtk);
            if (rtk != null) { // 리프레시 토큰 있으면
                // 액세스 토큰 재발급
                username = JWT.require(Algorithm.HMAC512(JwtProperties.REFRESH_TOKEN_SECRET)).build().verify(rtk)
                        .getClaim("username").asString();
                member = memberRepository.findByEmail(username).orElse(null);
                String accessToken = JWT.create()
                        .withSubject(member.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                        .withClaim("id", member.getId())
                        .withClaim("username", member.getEmail())
                        .withClaim("nickname", member.getNickname())
                        .sign(Algorithm.HMAC512(JwtProperties.ACCESS_TOKEN_SECRET));
                System.out.println("ACCESS 토큰 재발급");
                response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);
            }
        }

        if (member != null) {
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
