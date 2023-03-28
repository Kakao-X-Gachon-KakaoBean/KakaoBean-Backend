package com.kakaobean.security.oauth2.user;

import com.kakaobean.core.member.domain.Gender;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract Gender getGender();

    public abstract Integer getAge();

    public abstract LocalDate getBirth();
}
