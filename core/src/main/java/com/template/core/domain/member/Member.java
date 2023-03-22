package com.template.core.domain.member;

import com.template.core.domain.BaseEntity;
import com.template.core.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Auth auth;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String email, String password, Role role) {
        super(BaseStatus.ACTIVE);
        this.auth = new Auth(email, password);
        this.role = role;
    }
}
