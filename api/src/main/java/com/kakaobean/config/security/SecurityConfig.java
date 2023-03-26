package com.kakaobean.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.security.RestAuthenticationEntryPoint;
import com.kakaobean.security.jwt.JwtProvider;
import com.kakaobean.security.local.*;
import com.kakaobean.security.oauth2.CustomOAuth2UserService;
import com.kakaobean.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.kakaobean.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.kakaobean.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@RequiredArgsConstructor
@EnableWebSecurity

public class SecurityConfig {

    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;
    private final OAuth2AuthenticationFailureHandler auth2AuthenticationFailureHandler;
    private final OAuth2AuthenticationSuccessHandler auth2AuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .headers()
                .frameOptions().disable()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint());

        http.authorizeRequests()
                //.antMatchers(HttpMethod.POST, "/members").permitAll()
                .antMatchers("/**").permitAll();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션 사용 안함

        http.oauth2Login()
                .authorizationEndpoint()
                //.baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(new CustomOAuth2UserService())
                .and()
                .successHandler(auth2AuthenticationSuccessHandler)
                .failureHandler(auth2AuthenticationFailureHandler);

        //http.oauth2Client();

        http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    LocalLoginFilter loginFilter() throws NoSuchAlgorithmException {
        LocalLoginFilter localLoginFilter = new LocalLoginFilter(authenticationManager(), objectMapper);
        localLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        return localLoginFilter;
    }

    @Bean
    AuthenticationManager authenticationManager() throws NoSuchAlgorithmException {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
        return new LocalAuthenticationProvider(userDetailsService(), passwordEncoder());
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new LocalUserDetailService(memberRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        return new BCryptPasswordEncoder(10, random);
    }

    @Bean
    LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler(objectMapper, jwtProvider);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws NoSuchAlgorithmException {
        return new CustomAuthenticationFilter(authenticationManager(), jwtProvider);
    }
}
