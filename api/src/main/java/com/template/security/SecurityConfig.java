package com.template.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.core.domain.member.MemberRepository;
import com.template.security.jwt.JwtProvider;
import com.template.security.jwt.JwtProviderImpl;
import com.template.security.local.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new LocalAuthenticationEntryPoint());


        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션 사용 안함


        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/members").permitAll()
                .anyRequest().hasRole("USER");


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
