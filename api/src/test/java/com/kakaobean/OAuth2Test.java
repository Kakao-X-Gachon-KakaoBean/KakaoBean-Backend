package com.kakaobean;

import com.kakaobean.config.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Import(SecurityConfig.class)
@SpringBootTest
public class OAuth2Test {

    @Autowired
    ClientRegistrationRepository registrationRepository;

    @Test
    void test(){
        ClientRegistration google = registrationRepository.findByRegistrationId("google");
        ClientRegistration kako = registrationRepository.findByRegistrationId("kakao");

        System.out.println("google.getProviderDetails() = " + google.getProviderDetails());
    }
}
