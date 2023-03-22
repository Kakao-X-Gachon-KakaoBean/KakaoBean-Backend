package com.template.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.security.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;
    private final JwtProvider jwtProvider;

    public LoginSuccessHandler(ObjectMapper mapper, JwtProvider jwtProvider) {
        this.mapper = mapper;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), new LoginResponseDto("Login succeeded.", jwtProvider.createToken(authentication)));
    }
}
