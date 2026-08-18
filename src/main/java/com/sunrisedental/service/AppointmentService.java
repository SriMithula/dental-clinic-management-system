package com.sunrisedental.service;

import java.sql.Connection;
import java.util.List;

import com.sunrisedental.dao.AppointmentDao;
import com.sunrisedental.dao.DentistDao;
import com.sunrisedental.dao.PatientDao;
import com.sunrisedental.dao.TreatmentDao;
import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.dto.CommonResponse;
import com.sunrisedental.dto.DentistDto;
import com.sunrisedental.dto.TreatmentDto;
import com.sunrisedental.util.DBConnection;

public class AppointmentService {
	private AppointmentDao appointmentDao;
    private DentistDao dentistDao;
    private TreatmentDao treatmentDao;
    private PatientDao patientDao;

	public AppointmentService() {
		this.appointmentDao = new AppointmentDao();
	    this.dentistDao = new DentistDao();
	    this.treatmentDao = new TreatmentDao();
	    this.patientDao= new PatientDao();
	}
	
   public List<DentistDto> getDentists() {
        return dentistDao.getActiveDentists();
    }

    public List<TreatmentDto> getTreatments() {
        return treatmentDao.getActiveTreatments();
    }
	
	public CommonResponse saveAppointment(AppointmentDealer dealer, int userId) {
		CommonResponse cr = new CommonResponse();
	    Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);
			
			if (appointmentDao.isAppointmentNoExists(dealer.appointmentNo)) {
				connection.rollback();
	            cr.status = false;
	            cr.error = "Appointment number already exists.";

	            return cr;
	        }
			
			if (appointmentDao.isAppointmentExists(dealer)) {
				connection.rollback();
	            cr.status = false;
	            cr.error = "This dentist already has an appointment at this date and time.";

	            return cr;
	        }
			if (dealer.patientId == -1) {

                int newPatientId = patientDao.createPatient(
                        dealer.patientName,
                        dealer.contactNo,
                        dealer.address
                );

                if (newPatientId <= 0) {
                    connection.rollback();
                    cr.status = false;
                    cr.error = "Failed to create patient.";

                    return cr;
                }

                // Set newly created patient ID
                dealer.patientId = newPatientId;
            }
			appointmentDao.saveAppointment(dealer, userId);
	
			cr.status = true;
            cr.extra = "Appointment created successfully";
            
		} catch (Exception e) {
			e.printStackTrace();
			cr.status = false;
		    cr.error = "Failed to create appointment";
		    try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (Exception rollbackException) {
	            rollbackException.printStackTrace();
	        }
		} finally {

	        try {
	            if (connection != null) {
	                connection.setAutoCommit(true);
	                connection.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		return cr;
	}

}
