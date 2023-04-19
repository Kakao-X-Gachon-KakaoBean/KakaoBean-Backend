package com.kakaobean.core.config.redis;

import com.kakaobean.core.member.domain.email.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisClusterProperties redisClusterProperties;

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration config = new RedisClusterConfiguration(redisClusterProperties.getNodes());
        config.setPassword(RedisPassword.of("4648"));
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(config);
        return lettuceConnectionFactory;
    }
}
