package com.kakaobean.acceptance.response;

import com.kakaobean.acceptance.AcceptanceTest;
import com.kakaobean.acceptance.member.MemberAcceptanceTask;
import com.kakaobean.member.dto.RegisterMemberRequest;
import com.kakaobean.unit.controller.factory.member.RegisterMemberRequestFactory;
import io.restassured.response.ExtractableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SurveyAcceptanceTest extends AcceptanceTest {

    @Test
    void registerSurvey(){

        //given
        RegisterMemberRequest request = RegisterMemberRequestFactory.createRequest();

        //when
        ExtractableResponse response = MemberAcceptanceTask.registerMemberTask(request, emailRepository);

        //given
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
