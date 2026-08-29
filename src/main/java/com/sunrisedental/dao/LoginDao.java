package com.sunrisedental.dao;

import com.sunrisedental.dto.UserDto;

public interface LoginDao {
	public UserDto findByUserName(String username);
}
