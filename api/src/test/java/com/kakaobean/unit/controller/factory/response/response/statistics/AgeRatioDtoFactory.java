package com.kakaobean.unit.controller.factory.response.response.statistics;


import com.kakaobean.core.response.application.dto.response.statistics.AgeRatioDto;
import com.kakaobean.core.response.application.dto.response.statistics.GenderRatioDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class AgeRatioDtoFactory {

    public static List<AgeRatioDto> createDtoList(){
        return List.of(
                new AgeRatioDto("10대",0),
                new AgeRatioDto("20대",67),
                new AgeRatioDto("30대", 33),
                new AgeRatioDto("40대",0),
                new AgeRatioDto("50대",0),
                new AgeRatioDto("60대",0),
                new AgeRatioDto("알수없음",0)
        );
    }
}
