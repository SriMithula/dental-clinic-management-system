package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunrisedental.dao.PatientDao;
import com.sunrisedental.dto.PatientDto;
import com.sunrisedental.util.DatabaseConnectionManager;

public class PatientDaoImpl implements PatientDao {

	@Override
    public List<PatientDto> searchPatient(String searchText) {

        List<PatientDto> patients = new ArrayList<>();

        String sql = """
                SELECT id, name, address, contact_number, status
                FROM patients
                WHERE CAST(id AS CHAR) LIKE ?
                   OR name LIKE ?
                   OR contact_number LIKE ?
                   AND status = 1
                ORDER BY name
                """;

        try {
        	
        	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

        	String searchPattern = "%" + searchText + "%";

            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	
                PatientDto patient = new PatientDto();
                patient.id = rs.getInt("id");
                patient.name = rs.getString("name");
                patient.tel = rs.getString("contact_number");
                patient.address = rs.getString("address");
                
                patients.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }
	
	@Override
    public int createPatient(String name,String contactNo,String address) {

        String sql = """
                INSERT INTO patients
                (name, contact_number, address, status)
                VALUES (?, ?, ?, 1)
                """;

        try {
        	
          	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
          	PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, contactNo);
            ps.setString(3, address);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}