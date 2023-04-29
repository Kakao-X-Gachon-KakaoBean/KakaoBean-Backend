package com.kakaobean.core.member.exception.member;

import lombok.Getter;

@Getter
public class InvalidAccessMemberException extends MemberException{

    private static final String message = "유효하지 않은 접근입니다.";
    private static final String errorCode = "M004";
    private static final Integer status = 400;

    public InvalidAccessMemberException() {
        super(message, status, errorCode);
    }
}
