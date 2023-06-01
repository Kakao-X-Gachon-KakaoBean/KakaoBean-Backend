package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class AgeRatioDto {

    private String name;

    private Integer value;

    @Builder
    public AgeRatioDto(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static List<AgeRatioDto> calculateRatio(List<Member> respondents,
                                                   Integer numberOfResponse) {

        List<AgeRatioDto> ageRatioDtos = new ArrayList<>();

        int numOf10 = 0;
        int numOf20 = 0;
        int numOf30 = 0;
        int numOf40 = 0;
        int numOf50 = 0;
        int numOf60 = 0;
        int numOfSocial = 0;

        for (Member respondent : respondents) {
            Integer age = respondent.getAge();
            if (age >= 10 && age < 20) {
                numOf10++;
            } else if (age >= 20 && age < 30) {
                numOf20++;
            } else if(age >= 30 && age < 40){
                numOf30++;
            } else if(age >= 40 && age < 50){
                numOf40++;
            }else if (age >= 50 && age < 60){
                numOf50++;
            }else if(age >= 60 && age < 70){
                numOf60++;
            }else{
                numOfSocial++;
            }
        }

        AgeRatioDto ratio10 = new AgeRatioDto("10대", Math.round((float) numOf10 / numberOfResponse * 100));
        ageRatioDtos.add(ratio10);
        AgeRatioDto ratio20 = new AgeRatioDto("20대", Math.round((float) numOf20 / numberOfResponse * 100));
        ageRatioDtos.add(ratio20);
        AgeRatioDto ratio30 = new AgeRatioDto("20대", Math.round((float) numOf30 / numberOfResponse * 100));
        ageRatioDtos.add(ratio30);
        AgeRatioDto ratio40 = new AgeRatioDto("30대", Math.round((float) numOf40 / numberOfResponse * 100));
        ageRatioDtos.add(ratio40);
        AgeRatioDto ratio50 = new AgeRatioDto("40대", Math.round((float) numOf50 / numberOfResponse * 100));
        ageRatioDtos.add(ratio50);
        AgeRatioDto ratio60 = new AgeRatioDto("50대", Math.round((float) numOf60 / numberOfResponse * 100));
        ageRatioDtos.add(ratio60);
        AgeRatioDto ratioSocial = new AgeRatioDto("알수없음", Math.round((float) numOfSocial / numberOfResponse * 100));
        ageRatioDtos.add(ratioSocial);

        return ageRatioDtos;
    }
}
