package com.kakaobean.unit.controller.factory.response.response.statistics;

import com.kakaobean.core.response.application.dto.response.statistics.AgeRatioDto;
import com.kakaobean.core.response.application.dto.response.statistics.GenderRatioDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GenderRatioDtoFactory {

    public static List<GenderRatioDto> createDtoList(){
        return List.of(
                new GenderRatioDto("남자", 50),
                new GenderRatioDto("여자", 50),
                new GenderRatioDto("알수없음",0)
        );
    }
}
