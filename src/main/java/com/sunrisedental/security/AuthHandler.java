package com.sunrisedental.security;

import com.sunrisedental.dto.UserDto;

public abstract class AuthHandler {
    protected AuthHandler nextHandler;

    public AuthHandler setNextHandler(AuthHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler; 
    }

    public abstract UserDto handleAuth(UserDto user, String rawPassword);
}