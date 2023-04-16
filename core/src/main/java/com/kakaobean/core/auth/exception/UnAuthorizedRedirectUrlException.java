package com.kakaobean.core.auth.exception;

import com.kakaobean.core.auth.exception.AuthException;

public class UnAuthorizedRedirectUrlException extends AuthException {

    private static final String message = "인증되지 않은 redirect url 입니다.";
    private static final Integer status = 401;
    private static final String errorCode = "A001";

    public UnAuthorizedRedirectUrlException() {
        super(message, status, errorCode);
    }
}
