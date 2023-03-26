package com.kakaobean.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.security.RestAuthenticationEntryPoint;
import com.kakaobean.security.jwt.JwtProvider;
import com.kakaobean.security.local.*;
import com.kakaobean.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.kakaobean.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.kakaobean.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@RequiredArgsConstructor
public class LegacySecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;
    private final OAuth2AuthenticationFailureHandler auth2AuthenticationFailureHandler;
    private final OAuth2AuthenticationSuccessHandler auth2AuthenticationSuccessHandler;

    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .httpBasic()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                        .permitAll()
                    .antMatchers("/auth/**", "/oauth2/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                        .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
                        .and()
                    .redirectionEndpoint()
                        .baseUri("/oauth2/callback/*")
                        .and()
                    .userInfoEndpoint()
                        //.userService(customOAuth2UserService)
                        .and()
                    .successHandler(auth2AuthenticationSuccessHandler)
                    .failureHandler(auth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    LocalLoginFilter loginFilter() throws Exception {
        LocalLoginFilter localLoginFilter = new LocalLoginFilter(authenticationManagerBean(), objectMapper);
        localLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        return localLoginFilter;
    }


    @Bean
    public UserDetailsService userDetailsService(){
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
}
