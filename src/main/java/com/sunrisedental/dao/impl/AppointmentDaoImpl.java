package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrisedental.dao.AppointmentDao;
import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.dto.AppointmentDto;
import com.sunrisedental.util.DatabaseConnectionManager;

public class AppointmentDaoImpl implements AppointmentDao {
	
	@Override
    public boolean isAppointmentExists(AppointmentDealer dealer) {

        String sql = """
                SELECT COUNT(*)
                FROM appointments
                WHERE dentists_id = ?
                  AND appointment_date = ?
                  AND appointment_time = ?
                """;

        try {
        	
        	Connection conn = DatabaseConnectionManager.getInstance().getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, dealer.getDentistId());
            ps.setDate(2, dealer.getAppointmentDate());
            ps.setTime(3, dealer.getAppointmentTime());

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
    
	@Override
    public boolean isAppointmentNoExists(String appointmentNo) {

        String sql = """
                SELECT COUNT(*)
                FROM appointments
                WHERE appointment_no = ?
                """;

        try {
        	
        	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        	PreparedStatement ps = connection.prepareStatement(sql);

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
    
	@Override
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

        try {
        	
           	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

        	ps.setString(1, dealer.getAppointmentNo());
        	ps.setDate(2, dealer.getAppointmentDate());
        	ps.setTime(3, dealer.getAppointmentTime());
            ps.setInt(4, dealer.getPatientId());
            ps.setInt(5, dealer.getDentistId());
            ps.setInt(6, userId);
            ps.setInt(7, dealer.getTreatmentId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save appointment", e);
        }
	}

	@Override
	public List<AppointmentDto> getAppointments() {
		
		List<AppointmentDto> appointments = new ArrayList<AppointmentDto>();

	    String sql = """
	        SELECT 
	            a.id,
	            a.appointment_no,
	            a.appointment_date,
	            a.appointment_time,
	            p.name AS patient_name,
	            p.contact_number as contact_no,
	            d.name AS dentist_name,
	            t.name AS treatment_name,
	            a.isFinalized as isFinalized
	        FROM appointments a
	        INNER JOIN patients p ON a.patients_id = p.id
	        INNER JOIN dentists d ON a.dentists_id = d.id
	        INNER JOIN treatments t ON a.treatments_id = t.id
	        WHERE a.status = 1
	        ORDER BY a.appointment_date DESC, a.appointment_time DESC
	    """;

	    try  {
	    	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {

	            AppointmentDto dto = new AppointmentDto();

	            dto.setId(rs.getInt("id"));
	            dto.setAppointmentNo(rs.getString("appointment_no"));
	            dto.setAppointmentDate(rs.getDate("appointment_date"));
	            dto.setAppointmentTime(rs.getTime("appointment_time"));
	            dto.setPatientName(rs.getString("patient_name"));
	            dto.setContactNo(rs.getString("contact_no"));
	            dto.setDentistName(rs.getString("dentist_name"));
	            dto.setTreatmentName(rs.getString("treatment_name"));
	            dto.setFinalized(rs.getBoolean("isFinalized"));

	            appointments.add(dto);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return appointments;
	}

	@Override
	public boolean finalizeAppointment(int appointmentId, Connection connection) {

	    String sql =
	            "UPDATE appointments " +
	            "SET isFinalized = 1 " +
	            "WHERE id = ? ";

	    try {
			
	    	PreparedStatement ps =  connection.prepareStatement(sql);
	    	
	    	ps.setInt(1, appointmentId);
	    	
	    	int rows = ps.executeUpdate();
	    	
	    	return rows > 0;
		} catch (Exception e) {
	        e.printStackTrace();
		}
	    
	    return false;
	    
	}

	@Override
	public AppointmentDto findById(int appointmentId, Connection connection) {
		
		String sql = """
			        SELECT 
			            a.id,
			            a.appointment_no,
			            a.appointment_date,
			            a.appointment_time,
			            p.name AS patient_name,
			            p.contact_number as contact_no,
			            d.name AS dentist_name,
			            t.name AS treatment_name,
			            a.isFinalized as isFinalized,
			            t.cost as treatment_cost,
			            d.consultation_fee as consultation_fee
			        FROM appointments a
			        INNER JOIN patients p ON a.patients_id = p.id
			        INNER JOIN dentists d ON a.dentists_id = d.id
			        INNER JOIN treatments t ON a.treatments_id = t.id
			        WHERE a.status = 1 AND a.id = ?
			        ORDER BY a.appointment_date DESC, a.appointment_time DESC
			    """;

			    try  {
			    	
			        PreparedStatement ps = connection.prepareStatement(sql);
			        
			        ps.setInt(1, appointmentId);
			        ResultSet rs = ps.executeQuery();
			        
			        if (rs.next()) {

			            AppointmentDto dto = new AppointmentDto();

			            dto.setId(rs.getInt("id"));
			            dto.setAppointmentNo(rs.getString("appointment_no"));
			            dto.setAppointmentDate(rs.getDate("appointment_date"));
			            dto.setAppointmentTime(rs.getTime("appointment_time"));
			            dto.setPatientName(rs.getString("patient_name"));
			            dto.setContactNo(rs.getString("contact_no"));
			            dto.setDentistName(rs.getString("dentist_name"));
			            dto.setTreatmentName(rs.getString("treatment_name"));
			            dto.setFinalized(rs.getBoolean("isFinalized"));
			            dto.setTreatmentCost(rs.getDouble("treatment_cost"));
			            dto.setConsultation_fee(rs.getDouble("consultation_fee"));

			            return dto;
			        }

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return null;
	}

}
