package com.sunrisedental.security;

import com.sunrisedental.dto.UserDto;
import com.sunrisedental.util.EncryptionUtil;

public class PasswordMatchHandler extends AuthHandler{

	@Override
	public UserDto handleAuth(UserDto user, String rawPassword) {
		try {
            String encryptedInput = EncryptionUtil.encrypt(rawPassword);
            if (encryptedInput.equals(user.getPassword())) {
                if (nextHandler != null) {
                    return nextHandler.handleAuth(user, rawPassword);
                }
                return user; 
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        
        System.out.println("Auth Failed: Incorrect password.");
        return null;
	}

}
