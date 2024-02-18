package com.innovation.mygym.exception.exception.member;

import com.innovation.mygym.exception.ApplicationException;
import com.innovation.mygym.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicatePhoneException extends ApplicationException {

    public DuplicatePhoneException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_PHONE, ErrorCode.DUPLICATE_PHONE.getMessage());
    }
}
