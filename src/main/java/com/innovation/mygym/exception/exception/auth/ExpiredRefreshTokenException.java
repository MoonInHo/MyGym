package com.innovation.mygym.exception.exception.auth;

import com.innovation.mygym.exception.ApplicationException;
import com.innovation.mygym.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExpiredRefreshTokenException extends ApplicationException {

    public ExpiredRefreshTokenException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.EXPIRED_REFRESH_TOKEN_ERROR, "리프레시 토큰이 존재하지 않거나 만료되었습니다.");
    }
}
