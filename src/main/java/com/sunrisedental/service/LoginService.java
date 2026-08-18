package com.sunrisedental.service;

import com.sunrisedental.dao.LoginDao;
import com.sunrisedental.dto.UserDto;

public class LoginService {
	private LoginDao loginDao;

	public LoginService() {
		this.loginDao = new LoginDao();
	}
	
	public UserDto authenticateUser(String username, String password) {
		UserDto user = null;
		
		try {
			user = this.loginDao.login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
