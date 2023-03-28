package com.kakaobean.security.oauth2.user;

import com.kakaobean.core.member.domain.Gender;

import java.time.LocalDate;
import java.util.Map;

import static com.kakaobean.core.member.domain.Gender.UNKNOWN;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    public Gender getGender() {
        return UNKNOWN;
    }

    @Override
    public Integer getAge() {
        return 0;
    }

    @Override
    public LocalDate getBirth() {
        return null;
    }
}
