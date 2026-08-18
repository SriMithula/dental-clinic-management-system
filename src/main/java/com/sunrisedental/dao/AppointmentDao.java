package com.sunrisedental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.util.DBConnection;

public class AppointmentDao {
    public boolean isAppointmentExists(AppointmentDealer dealer) {

        String sql = """
                SELECT COUNT(*)
                FROM appointments
                WHERE dentists_id = ?
                  AND appointment_date = ?
                  AND appointment_time = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, dealer.dentistId);
            ps.setDate(2, dealer.appointmentDate);
            ps.setTime(3, dealer.appointmentTime);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to validate appointment", e);
        }

        return false;
    }
    
    public boolean isAppointmentNoExists(String appointmentNo) {

        String sql = """
                SELECT COUNT(*)
                FROM appointments
                WHERE appointment_no = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, appointmentNo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to validate appointment number", e);
        }

        return false;
    }
    
	public void saveAppointment(AppointmentDealer dealer, int userId) {
		
		String sql = """
	            INSERT INTO appointments
	                (
	                    appointment_no,
	                    appointment_date,
	                    appointment_time,
	                    patients_id,
	                    dentists_id,
	                    created_user_id,
	                    treatments_id
	                )
	                VALUES (?, ?, ?, ?, ?, ?, ?)
	                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {

        	ps.setString(1, dealer.appointmentNo);
        	ps.setDate(2, dealer.appointmentDate);
        	ps.setTime(3, dealer.appointmentTime);
            ps.setInt(4, dealer.patientId);
            ps.setInt(5, dealer.dentistId);
            ps.setInt(6, userId);
            ps.setInt(7, dealer.treatmentId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save appointment", e);
        }
	}

}
