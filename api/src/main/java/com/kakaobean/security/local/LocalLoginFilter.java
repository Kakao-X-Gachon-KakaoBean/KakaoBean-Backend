package com.kakaobean.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LocalLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public LocalLoginFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/login/local", "POST"), authenticationManager);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response
    ) throws AuthenticationException, IOException {
        LoginRequestDto loginDto = objectMapper.readValue(request.getReader(), LoginRequestDto.class);
        validateLoginRequestDto(loginDto);
        JwtAuthenticationToken token = new JwtAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    private void validateLoginRequestDto(
            LoginRequestDto loginDto
    ) {
        if(!StringUtils.hasText(loginDto.getEmail()) || !StringUtils.hasText(loginDto.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
    }
}
