package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sunrisedental.dao.DashboardDao;
import com.sunrisedental.dto.DashboardDto;
import com.sunrisedental.util.DatabaseConnectionManager;

public class DashboardDaoImpl implements DashboardDao {

	@Override
    public DashboardDto getDashboardData() {

        DashboardDto dashboard = new DashboardDto();

        String appointmentSql = """
                SELECT COUNT(*)
                FROM appointments 
                WHERE status = 1
                """;

        String patientSql = """
                SELECT COUNT(*)
                FROM patients
                WHERE status = 1
                """;

        String todayVisitSql = """
                SELECT COUNT(*)
                FROM appointments
                WHERE appointment_date = CURDATE()
                """;

        String revenueSql = """
                SELECT COALESCE(SUM(total_amount), 0)
                FROM invoice
                WHERE status = 1
                """;

        try  {
        	
        	Connection connection = DatabaseConnectionManager.getInstance().getConnection();

            // Total appointments
            try (PreparedStatement ps =
                         connection.prepareStatement(appointmentSql);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    dashboard.setTotalAppointments(rs.getInt(1));
                }
            }


            // Total patients
            try (PreparedStatement ps =
                         connection.prepareStatement(patientSql);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    dashboard.setTotalPatients(rs.getInt(1));
                }
            }


            // Today's visits
            try (PreparedStatement ps =
                         connection.prepareStatement(todayVisitSql);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    dashboard.setTodayVisits(rs.getInt(1));
                }
            }


            // Revenue
            try (PreparedStatement ps =
                         connection.prepareStatement(revenueSql);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    dashboard.setRevenue(rs.getDouble(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dashboard;
    }
}