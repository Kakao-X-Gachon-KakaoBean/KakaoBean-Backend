package com.template.security.local;

import com.template.security.JwtAuthenticationToken;
import com.template.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class LocalAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(authentication.getName());
        if(isNotSamePassword(authentication.getCredentials().toString(), memberContext.getPassword())){
            throw new RuntimeException("비밀번호가 다릅니다");
        }

        return new JwtAuthenticationToken(memberContext.getMemberId(), null, memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }

    private boolean isNotSamePassword(String rawPassword, String encoderPassword){
        return !passwordEncoder.matches(rawPassword, encoderPassword); //rawPassword, encoderPassword 순으로 인자를 넣는다.
    }
}
