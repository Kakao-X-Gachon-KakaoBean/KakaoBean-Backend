package com.kakaobean.core.response.application.dto.response.statistics;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnswerStatisticsDto {

    private String name;

    private Integer value;

    @Builder
    public AnswerStatisticsDto(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
