package com.sunrisedental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sunrisedental.dto.UserDto;
import com.sunrisedental.util.DBConnection;
import com.sunrisedental.util.EncryptionUtil;

public class LoginDao {

    public UserDto login(String username, String password) {

        String sql = """
                SELECT id, username, password
                FROM users
                WHERE username = ?
        		AND status = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setBoolean(2, true);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    String dbPassword =
                            resultSet.getString("password");

                    String encryptedPassword =
                            EncryptionUtil.encrypt(password);

                    if (encryptedPassword.equals(dbPassword)) {

                        UserDto user = new UserDto();

                        user.setId(resultSet.getInt("id"));
                        user.setUsername(
                                resultSet.getString("username")
                        );

                        return user;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}