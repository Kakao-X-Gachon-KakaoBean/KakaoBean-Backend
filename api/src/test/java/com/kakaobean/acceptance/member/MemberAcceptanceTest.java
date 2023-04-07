package com.kakaobean.acceptance.member;

import com.kakaobean.acceptance.AcceptanceTest;
import com.kakaobean.member.dto.RegisterMemberRequest;
import com.kakaobean.unit.controller.factory.member.RegisterMemberDtoFactory;

import io.restassured.response.ExtractableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberAcceptanceTest extends AcceptanceTest {

    @Test
    void registerMember(){

        //given
        RegisterMemberRequest request = RegisterMemberDtoFactory.createRequest();

        //when
        ExtractableResponse response = MemberAcceptanceTask.registerMemberTask(request);

        //given
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
