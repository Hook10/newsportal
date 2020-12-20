package com.newsportal.exception;

import java.security.GeneralSecurityException;

public class LoginException extends GeneralSecurityException {


    public LoginException(String msg){
        super(msg);
    }
}
