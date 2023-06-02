package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class GenderRatioDto {

    private String name;

    private Integer value;

    @Builder
    public GenderRatioDto(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static List<GenderRatioDto> calculateRatio(List<Member> respondents,
                                                      Integer numberOfResponse){

        List<GenderRatioDto> genderRatioDto = new ArrayList<>();

        int numOfMale = 0;
        int numOfFemale = 0;
        int socialGender = 0;

        for (Member respondent : respondents) {
            Gender gender = respondent.getGender();
            if (gender == Gender.MALE) {
                numOfMale++;
            } else if (gender == Gender.FEMALE){
                numOfFemale++;
            }else {
                socialGender++;
            }
        }

        GenderRatioDto maleRatio = new GenderRatioDto("남자", Math.round((float) numOfMale/numberOfResponse*100));
        genderRatioDto.add(maleRatio);
        GenderRatioDto femaleRatio = new GenderRatioDto("여자", Math.round((float) numOfFemale/numberOfResponse*100));
        genderRatioDto.add(femaleRatio);
        GenderRatioDto socialRatio = new GenderRatioDto("알수없음", Math.round((float) socialGender/numberOfResponse*100));
        genderRatioDto.add(socialRatio);

        return genderRatioDto;
    }
}
