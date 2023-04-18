package com.kakaobean.independentlysystem.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

@RequiredArgsConstructor
public class EmailRedisTemplate {

    private final StringRedisTemplate redisTemplate;
}
