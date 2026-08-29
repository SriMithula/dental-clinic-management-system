package com.sunrisedental.service;

import com.sunrisedental.dao.LoginDao;
import com.sunrisedental.dao.impl.LoginDaoImpl;
import com.sunrisedental.dto.UserDto;
import com.sunrisedental.security.*;

public class LoginService {
    private LoginDao loginDao;
    private AuthHandler authChain;

    public LoginService() {
        this.loginDao = new LoginDaoImpl();
        
        AuthHandler userCheck = new UserValidationHandler();
        AuthHandler passwordCheck = new PasswordMatchHandler();
        
        // Link them together
        userCheck.setNextHandler(passwordCheck);
        this.authChain = userCheck; 
    }
    
    public UserDto authenticateUser(String username, String password) {
   
        UserDto fetchedUser = this.loginDao.findByUserName(username);
        

        return this.authChain.handleAuth(fetchedUser, password);
    }
}