package com.template.security.local;

import com.template.core.domain.member.Member;
import com.template.core.domain.member.MemberRepository;
import com.template.core.domain.member.Role;
import com.template.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor
public class LocalUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("존재하지 않는 유저다.")
                );
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getRole().name());
        return new MemberContext(member, authority);
    }
}
