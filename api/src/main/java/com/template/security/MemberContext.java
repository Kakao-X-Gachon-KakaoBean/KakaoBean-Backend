package com.template.security;

import com.template.core.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MemberContext implements UserDetails {

    private final Member member;
    private final SimpleGrantedAuthority authority;


    public MemberContext(Member member, SimpleGrantedAuthority authority) {
        this.member = member;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.authority);
    }

    @Override
    public String getPassword() {
        return member.getAuth().getPassword();
    }

    @Override
    public String getUsername() {
        return member.getAuth().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Long getMemberId() {
        return member.getId();
    }

}
