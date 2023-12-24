package com.devinhouse.M02S10.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailException extends AuthenticationException {

    public AuthenticationFailException(String msg) {
        super(msg);
    }
}