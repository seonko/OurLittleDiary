package com.seonko.OurLittleDiary.config.jwt;

public interface JwtProperties {
    String ACCESS_TOKEN_SECRET = "아리다액세스";
    String REFRESH_TOKEN_SECRET = "아리다시크릿";
    int ACCESS_TOKEN_EXPIRATION_TIME = 1000000000; // 10초
    int REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 30; // 1개월
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
