package com.kakaobean.core.survey.domain;


import com.kakaobean.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyOwner{

    private Long memberId;

    public SurveyOwner(Long memberId) {
        this.memberId = memberId;
    }
}
