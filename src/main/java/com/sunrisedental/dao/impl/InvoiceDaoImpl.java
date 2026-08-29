package com.sunrisedental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sunrisedental.dao.InvoiceDao;
import com.sunrisedental.dto.PatientBill;
import com.sunrisedental.util.DatabaseConnectionManager;

public class InvoiceDaoImpl implements InvoiceDao{

	@Override
	public int createInvoice(int appointmentId, double treatmentCost, double consultation_fee) {
		
		String sql = """
	            INSERT INTO invoice
	                (
	                    treatment_cost,
	                    consultation_fee,
	                    status,
	                    total_amount,
	                    appointments_id
	                )
	                VALUES (?, ?, ?, ?, ?)
	                """;

        try {
        	
           	Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

        	ps.setDouble(1, treatmentCost);
        	ps.setDouble(2, consultation_fee);
        	ps.setBoolean(3, true);
            ps.setDouble(4, (treatmentCost + consultation_fee));
            ps.setInt(5, appointmentId);

            int affectedRows = ps.executeUpdate();
            
            if (affectedRows == 0) {
                throw new RuntimeException("Failed to create invoice");
            }
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save appointment", e);
        }
        
        return -1;
	}

	@Override
	public PatientBill getInvoiceByAppointmentId(int appointmentId) {
		 	String sql = """
			         SELECT
			            a.appointment_no,
			            p.name AS patient_name,
			            t.name AS treatment_name,
			            i.treatment_cost,
			            i.consultation_fee
			        FROM invoice i
			        INNER JOIN appointments a
			            ON a.id = i.appointments_id
			        INNER JOIN patients p
			            ON p.id = a.patients_id
			        INNER JOIN treatments t
			            ON t.id = a.treatments_id
			        WHERE a.id = ?
			        LIMIT 1
			        """;
			    try {
			        Connection connection =
			                DatabaseConnectionManager.getInstance().getConnection();

			        PreparedStatement ps = connection.prepareStatement(sql);

			        ps.setInt(1, appointmentId);

			        ResultSet rs = ps.executeQuery();

			        if (rs.next()) {

			        	 return new PatientBill(
			                     rs.getString("appointment_no"),
			                     rs.getString("patient_name"),
			                     rs.getString("treatment_name"),
			                     rs.getDouble("treatment_cost"),
			                     rs.getDouble("consultation_fee")
			                 );
			        }

			    } catch (SQLException e) {
			        e.printStackTrace();
			        throw new RuntimeException(
			            "Failed to find bill for appointment: " + appointmentId,
			            e
			        );
			    }

			    return null;
	}

}
