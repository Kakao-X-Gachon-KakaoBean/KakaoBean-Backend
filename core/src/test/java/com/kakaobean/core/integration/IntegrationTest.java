package com.kakaobean.core.integration;

import com.kakaobean.independentlysystem.email.EmailSender;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Import({EmailSender.class, })
@SpringBootTest
@Transactional
public abstract class IntegrationTest {
}
