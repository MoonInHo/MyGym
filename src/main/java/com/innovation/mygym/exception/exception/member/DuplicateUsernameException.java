package com.innovation.mygym.exception.exception.member;

import com.innovation.mygym.exception.ApplicationException;
import com.innovation.mygym.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends ApplicationException {

    public DuplicateUsernameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_USERNAME_ERROR, "해당 아이디가 이미 존재합니다.");
    }
}
