package com.template.core.service.member;


import com.template.core.domain.member.MemberRepository;
import com.template.core.domain.member.Member;
import com.template.core.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long registerMember(
            RegisterMemberRequestDto requestDto
    ){
        Member member = new Member(requestDto.getEmail(), requestDto.getPassword(), Role.ROLE_USER);
        memberRepository.save(member);
        return member.getId();
    }
}
