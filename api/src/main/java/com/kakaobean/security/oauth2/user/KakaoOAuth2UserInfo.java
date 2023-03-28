package com.kakaobean.security.oauth2.user;

import com.kakaobean.core.member.domain.Gender;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class KakaoOAuth2UserInfo  extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        Map properties = (Map) attributes.get("properties");
        return (String) properties.get("nickname");
    }

    @Override
    public String getEmail() {
        Map kakaoAccount = (Map) attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    public Gender getGender() {
        Map kakaoAccount = (Map) attributes.get("kakao_account");
        String gender = (String) kakaoAccount.get("gender");
        return Gender.valueOf(gender.toUpperCase(Locale.ROOT));
    }

    @Override
    public Integer getAge() {
        Map kakaoAccount = (Map) attributes.get("kakao_account");
        String ageRange= (String) kakaoAccount.get("age_range");
        return Integer.parseInt(ageRange.substring(0,2));
    }

    @Override
    public LocalDate getBirth() {
        return null;
    }
}