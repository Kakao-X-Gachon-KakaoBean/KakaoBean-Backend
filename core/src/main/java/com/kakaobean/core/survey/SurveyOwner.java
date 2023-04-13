package com.kakaobean.core.survey;


import com.kakaobean.core.common.domain.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Embeddable
public class SurveyOwner{

    private Long memberId;

}
