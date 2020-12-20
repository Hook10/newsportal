package com.newsportal.controllers;

import com.newsportal.config.TestConfig;
import com.newsportal.entity.NewsUser;
import com.newsportal.exception.EmailNotFoundException;
import com.newsportal.exception.LoginException;
import com.newsportal.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class NewsUserControllerTest {

    @Autowired
    private NewsUserController controller;

    @Autowired
    private UserService userService;

    private static final String userEmail = "user@gmail.com";
    private static final NewsUser user = mock(NewsUser.class);
    private static final String passwordUser = "password`";
    private static final String emptyEmail = "";


    @Before
    public void setUp(){
        when(user.getEmail()).thenReturn(userEmail);
        when(userService.fetchUserByEmail(emptyEmail)).thenThrow(new EmailNotFoundException("email doesn't exist"));
    }

    @Test
    void loginUserWithExistEmail() throws LoginException {
        user.setPassword("password");
        when(userService.fetchUserByEmail(userEmail)).thenReturn(user);
        Errors errors = mock(Errors.class);
        when(controller.userPasswordValidation(passwordUser, user)).thenReturn(user);
    }



    @Test
    public void loginUserWhenExceptionThrown() {
        Exception exception =  assertThrows(EmailNotFoundException.class, () -> {
            NewsUser userFromDB = Optional.ofNullable(userService.fetchUserByEmail(emptyEmail))
                    .orElseThrow(() -> new EmailNotFoundException("email doesn't exist"));
                    });

        String expectedMessage = "email doesn't exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}