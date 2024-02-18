package com.innovation.mygym.exception.exception.member;

import com.innovation.mygym.exception.ApplicationException;
import com.innovation.mygym.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends ApplicationException {

    public DuplicateUsernameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_USERNAME, ErrorCode.DUPLICATE_USERNAME.getMessage());
    }
}
