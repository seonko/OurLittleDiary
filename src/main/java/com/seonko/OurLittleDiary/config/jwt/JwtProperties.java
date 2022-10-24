package com.seonko.OurLittleDiary.config.jwt;

public interface JwtProperties {
    String SECRET = "아리다";
    int EXPIRATION_TIME = 864000000; // 10일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
