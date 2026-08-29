package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.sunrisedental.dao.LoginDao;
import com.sunrisedental.dto.UserDto;
import com.sunrisedental.util.DatabaseConnectionManager;

public class LoginDaoImpl implements LoginDao {

    @Override
    public UserDto findByUserName(String username) {
    	
        String sql = """
                SELECT id, username, password, status
                FROM users
                WHERE username = ?
                AND status = ?
                """;

        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setBoolean(2, true); 

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserDto user = new UserDto();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password")); 
                    
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}