package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrisedental.dao.DentistDao;
import com.sunrisedental.dto.DentistDto;
import com.sunrisedental.util.DatabaseConnectionManager;

public class DentistDaoImpl implements DentistDao {

	@Override
    public List<DentistDto> getActiveDentists() {

        List<DentistDto> dentists = new ArrayList<>();

        String sql = """
                SELECT id, name, contact_number
                FROM dentists
                WHERE status = 1
                ORDER BY name
                """;

        try {
        	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        	PreparedStatement ps = connection.prepareStatement(sql);
        	ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DentistDto dentist = new DentistDto();

                dentist.setId(rs.getInt("id"));
                dentist.setName(rs.getString("name"));
                dentist.setContactNumber(
                    rs.getString("contact_number")
                );

                dentists.add(dentist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dentists;
    }
}