package com.kakaobean;

import com.kakaobean.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

@Import(SecurityConfig.class)
@SpringBootTest
public class OAuth2Test {

    @Autowired
    ClientRegistrationRepository registrationRepository;

    @Autowired
    DelegatingFilterProxy filter;

    @Test
    void test(){
        ClientRegistration google = registrationRepository.findByRegistrationId("google");
        System.out.println("google.getProviderDetails() = " + google.getProviderDetails());
        System.out.println("filter = " + filter);
    }
}
