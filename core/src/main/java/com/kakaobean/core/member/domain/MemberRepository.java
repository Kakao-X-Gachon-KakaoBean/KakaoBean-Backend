package com.kakaobean.core.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.auth.email = :email and m.status = 'ACTIVE'")
    Optional<Member> findMemberByEmail(String email);
}
