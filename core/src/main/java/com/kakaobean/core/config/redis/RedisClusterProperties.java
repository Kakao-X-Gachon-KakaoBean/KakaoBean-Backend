package com.kakaobean.core.config.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Getter @Setter
public class RedisClusterProperties {
    List<String> nodes;
    String password;
}
