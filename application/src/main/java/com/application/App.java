package com.application;

import com.auth.security.AuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com")
@EnableJpaRepositories(basePackages = "com")
@ComponentScan(basePackages = "com")
@EnableConfigurationProperties(AuthProperties.class)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
