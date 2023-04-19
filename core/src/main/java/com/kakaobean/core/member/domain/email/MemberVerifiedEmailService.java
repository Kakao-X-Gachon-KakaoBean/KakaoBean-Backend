package com.kakaobean.core.member.domain.email;

public interface MemberVerifiedEmailService {
    void sendVerificationEmail(String receiveEmail);
    void verifyAuthEmailKey(String email, String authKey);
}
