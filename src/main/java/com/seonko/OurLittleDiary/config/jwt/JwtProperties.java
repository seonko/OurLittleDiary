package com.seonko.OurLittleDiary.config.jwt;

public interface JwtProperties {
    String ACCESS_TOKEN_SECRET = "아리다액세스";
    String REFRESH_TOKEN_SECRET = "아리다리프레시";
    int ACCESS_TOKEN_EXPIRATION_TIME = 60000 * 60; // 1시간
    int REFRESH_TOKEN_EXPIRATION_TIME = 60000 * 60 * 24; // 1일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
