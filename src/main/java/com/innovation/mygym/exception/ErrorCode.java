package com.innovation.mygym.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Global
    INVALID_REQUEST("잘못된 요청"),

    // Member
    DUPLICATE_USERNAME("해당 아이디가 이미 존재합니다."),
    DUPLICATE_PHONE("해당 연락처로 가입 정보가 이미 존재합니다."),

    // Authorization
    EXPIRED_ACCESS_TOKEN("액세스 토큰이 만료 되었거나 존재하지 않습니다."),
    EXPIRED_REFRESH_TOKEN("리프레시 토큰이 만료 되었거나 존재하지 않습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}