package com.kakaobean.core.response.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Respondent{

    private Long memberId;

    public Respondent(Long memberId) {
        this.memberId = memberId;
    }
}
