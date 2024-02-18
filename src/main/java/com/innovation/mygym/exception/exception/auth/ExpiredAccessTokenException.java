package com.innovation.mygym.exception.exception.auth;

import com.innovation.mygym.exception.ApplicationException;
import com.innovation.mygym.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExpiredAccessTokenException extends ApplicationException {

    public ExpiredAccessTokenException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.EXPIRED_ACCESS_TOKEN, "액세스 토큰이 존재하지 않거나 만료되었습니다.");
    }
}
