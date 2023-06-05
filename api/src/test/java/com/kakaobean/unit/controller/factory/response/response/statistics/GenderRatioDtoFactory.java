package com.kakaobean.unit.controller.factory.response.response.statistics;

import com.kakaobean.core.response.application.dto.response.statistics.GenderRatioDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GenderRatioDtoFactory {

    public static List<GenderRatioDto> createDtoList(){
        return List.of(
                new GenderRatioDto("10대",0),
                new GenderRatioDto("20대",67),
                new GenderRatioDto("30대", 33),
                new GenderRatioDto("40대",0),
                new GenderRatioDto("50대",0),
                new GenderRatioDto("60대",0),
                new GenderRatioDto("알수없음",0)
        );
    }
}
