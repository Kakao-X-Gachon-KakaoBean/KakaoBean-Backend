package com.kakaobean.unit.controller.factory.response.response.statistics;


import com.kakaobean.core.response.application.dto.response.statistics.AgeRatioDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class AgeRatioDtoFactory {

    public static List<AgeRatioDto> createDtoList(){
        return List.of(
                new AgeRatioDto("남자", 50),
                new AgeRatioDto("여자", 50),
                new AgeRatioDto("알수없음",0)
        );
    }
}
