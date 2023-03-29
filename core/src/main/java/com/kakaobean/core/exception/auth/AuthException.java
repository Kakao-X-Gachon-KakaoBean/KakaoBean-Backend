package com.kakaobean.core.exception.auth;

import com.kakaobean.core.exception.ApplicationException;
import lombok.Getter;

@Getter
public abstract class AuthException extends ApplicationException {

    public AuthException(String message, Integer status, String errorCode) {
        super(message, status, errorCode);
    }
}
