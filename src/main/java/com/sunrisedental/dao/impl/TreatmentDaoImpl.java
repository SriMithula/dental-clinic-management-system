package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrisedental.dao.TreatmentDao;
import com.sunrisedental.dto.TreatmentDto;
import com.sunrisedental.util.DatabaseConnectionManager;

public class TreatmentDaoImpl implements TreatmentDao {

	@Override
    public List<TreatmentDto> getActiveTreatments() {

        List<TreatmentDto> treatments = new ArrayList<>();

        String sql = """
                SELECT id, name, cost
                FROM treatments
                WHERE status = 1
                ORDER BY name
                """;

        try  {
        	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        	PreparedStatement ps = connection.prepareStatement(sql);
        	ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TreatmentDto treatment = new TreatmentDto();

                treatment.setId(rs.getInt("id"));
                treatment.setName(rs.getString("name"));
                treatment.setCost(rs.getDouble("cost"));

                treatments.add(treatment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return treatments;
    }
}