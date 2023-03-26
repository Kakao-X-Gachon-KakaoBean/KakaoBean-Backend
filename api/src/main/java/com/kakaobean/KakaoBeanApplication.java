package com.kakaobean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.List;

@EnableJpaAuditing

@SpringBootApplication
@ConfigurationPropertiesScan
public class KakaoBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoBeanApplication.class, args);
    }

}
