package com.kakaobean.core.exception.member;

import com.kakaobean.core.exception.ApplicationException;
import lombok.Getter;

@Getter
public abstract class MemberException extends ApplicationException {

    public MemberException(String message, Integer status, String errorCode) {
        super(message, status, errorCode);
    }
}
