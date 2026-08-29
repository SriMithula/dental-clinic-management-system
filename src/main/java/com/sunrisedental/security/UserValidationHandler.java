package com.sunrisedental.security;

import com.sunrisedental.dto.UserDto;

public class UserValidationHandler  extends AuthHandler{

	@Override
	public UserDto handleAuth(UserDto user, String rawPassword) {
		if (user == null) {
            System.out.println("Auth Failed: User not found or inactive.");
            return null; 
        }
        if (nextHandler != null) {
            return nextHandler.handleAuth(user, rawPassword);
        }
        return user;
	}

}
