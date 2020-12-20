package com.newsportal.config;

import com.newsportal.controllers.NewsUserController;
import com.newsportal.entity.NewsUser;
import com.newsportal.repository.NewsUserRepository;
import com.newsportal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.mock;

@Configuration
//@WebAppConfiguration
//@SpringBootTest
public class TestConfig {


    @Bean
    public NewsUserRepository newsUserRepository() {
        return mock(NewsUserRepository.class);
    }

    @Bean
    public NewsUserController newsUserController(){
        return mock(NewsUserController.class);
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
