package com.kakaobean.acceptance.member;

import com.kakaobean.member.dto.RegisterMemberRequest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class MemberAcceptanceTask {

    private MemberAcceptanceTask(){}

    static public ExtractableResponse registerMemberTask(RegisterMemberRequest request){
        return RestAssured
                .given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/members")
                .then().log().all()
                .extract();
    }
}
