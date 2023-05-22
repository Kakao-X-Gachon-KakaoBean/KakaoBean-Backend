package com.kakaobean.core.member.infrastructure;

import com.kakaobean.core.member.domain.Email;
import com.kakaobean.core.member.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisEmailRepository implements EmailRepository {

    private final String PREFIX = "email:";
    private final int LIMIT_TIME = 5 * 600; //유효시간 설정

    private final StringRedisTemplate redisTemplate;
    //SMS 인증 정보 저장
    @Override
    public void save(Email email) {
        redisTemplate.opsForValue().set(PREFIX + email.getEmail(), email.getAuthKey(), Duration.ofSeconds(LIMIT_TIME));
    }

    //Redis에 있는 값을 리턴
    @Override
    public Email getEmailCertification(Email email) {
        String authKey = redisTemplate.opsForValue().get(PREFIX + email.getEmail());
        return new Email(email.getEmail(), authKey);
    }

    //제거
    @Override
    public void removeEmailCertification(Email email) {
        redisTemplate.delete(PREFIX + email.getEmail());
    }

    //키에 대한 값을 가지고 있는가
    @Override
    public boolean hasKey(Email email) {
        return redisTemplate.hasKey(PREFIX + email.getEmail());
    }
}
