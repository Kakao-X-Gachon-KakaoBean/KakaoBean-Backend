package com.kakaobean.core.member.infrastructure;

import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.service.ModifyMemberService;
import com.kakaobean.core.member.exception.member.DiffrentPasswordAndCheckPasswordException;
import com.kakaobean.core.member.exception.member.NotValidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
//@RequiredArgsConstructor
public class ModifyMemberServiceImpl implements ModifyMemberService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 테스트를 위한 임시 방편

    @Override
    public void modifyPassword(Member member, 
                               String nowPassword, 
                               String passwordToChange, 
                               String checkPasswordToChange) {
        String password = member.getAuth().getPassword();
        
        if(nowPasswordIsNotRight(nowPassword, password)){
            throw new NotValidPasswordException();
        }
        
        if(passwordChangeValidationFailed(passwordToChange, checkPasswordToChange)){
            throw new DiffrentPasswordAndCheckPasswordException();
        }

        member.updatePassword(passwordEncoder.encode(passwordToChange));
    }

    private boolean passwordChangeValidationFailed(String passwordToChange, String checkPasswordToChange) {
        return !passwordToChange.equals(checkPasswordToChange);
    }

    private boolean nowPasswordIsNotRight(String nowPassword, String password) {
        return !passwordEncoder.matches(nowPassword, password);
    }

}
