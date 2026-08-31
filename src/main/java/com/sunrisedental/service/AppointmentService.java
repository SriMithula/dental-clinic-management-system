package com.sunrisedental.service;

import java.sql.Connection;
import java.util.List;

import com.sunrisedental.dao.AppointmentDao;
import com.sunrisedental.dao.DentistDao;
import com.sunrisedental.dao.InvoiceDao;
import com.sunrisedental.dao.PatientDao;
import com.sunrisedental.dao.TreatmentDao;
import com.sunrisedental.dao.impl.AppointmentDaoImpl;
import com.sunrisedental.dao.impl.DentistDaoImpl;
import com.sunrisedental.dao.impl.InvoiceDaoImpl;
import com.sunrisedental.dao.impl.PatientDaoImpl;
import com.sunrisedental.dao.impl.TreatmentDaoImpl;
import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.dto.AppointmentDto;
import com.sunrisedental.dto.CommonResponse;
import com.sunrisedental.dto.DentistDto;
import com.sunrisedental.dto.TreatmentDto;
import com.sunrisedental.util.DBConnection;
import com.sunrisedental.util.DatabaseConnectionManager;

public class AppointmentService {
	private AppointmentDao appointmentDao;
    private DentistDao dentistDao;
    private TreatmentDao treatmentDao;
    private PatientDao patientDao;
    private InvoiceDao invoiceDao;

	public AppointmentService() {
		this.appointmentDao = new AppointmentDaoImpl();
	    this.dentistDao = new DentistDaoImpl();
	    this.treatmentDao = new TreatmentDaoImpl();
	    this.patientDao= new PatientDaoImpl();
	    this.invoiceDao = new InvoiceDaoImpl();
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
			
			if (appointmentDao.isAppointmentNoExists(dealer.getAppointmentNo())) {
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
			if (dealer.getPatientId() == -1) {
				
				 if (patientDao.isPatientNameExists(dealer.getPatientName())) {
				        connection.rollback();
				        cr.status = false;
				        cr.error = "A patient with this name already exists. Please search for the patient above and select them instead of registering as new.";

				        return cr;
				    }


                int newPatientId = patientDao.createPatient(
                        dealer.getPatientName(),
                        dealer.getContactNo(),
                        dealer.getAddress()
                );

                if (newPatientId <= 0) {
                    connection.rollback();
                    cr.status = false;
                    cr.error = "Failed to create patient.";

                    return cr;
                }

                // Set newly created patient ID
                dealer.setPatientId(newPatientId);
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
	
	public CommonResponse getAppointments() {

	    CommonResponse cr = new CommonResponse();
	    Connection connection = null;

	    try {

	        connection = DBConnection.getConnection();

	        List<AppointmentDto> appointments = appointmentDao.getAppointments();

	        cr.status = true;
	        cr.extra = appointments;

	    } catch (Exception e) {

	        e.printStackTrace();

	        cr.status = false;
	        cr.error = "Failed to load appointments.";

	    } finally {

	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return cr;
	}
	public CommonResponse finalizeAppointment(int appointmentId) {

        CommonResponse cr = new CommonResponse();
                

        Connection connection = null;

        try {

           	connection = DatabaseConnectionManager.getInstance().getConnection();

            connection.setAutoCommit(false);

            

            boolean updated = appointmentDao.finalizeAppointment(appointmentId, connection);

            if (!updated) {

                connection.rollback();

                cr.status = false;

                cr.error = "Appointment could not be finalized.";

                return cr;
            }
            
            AppointmentDto appointment = appointmentDao.findById(appointmentId, connection);
            if(appointment == null) {
	        	  connection.rollback();
	
	              cr.status = false;
	
	              cr.error = "Appointment notfound!.";
	
	              return cr;
            }
            
            invoiceDao.createInvoice(appointmentId, appointment.getTreatmentCost(), appointment.getConsultation_fee());

            connection.commit();

            cr.status = true;

            cr.extra = "Appointment finalized successfully.";
  

        } catch (Exception e) {

            e.printStackTrace();

            cr.status = false;

            cr.error =  "Failed to finalize appointment.";
                   

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
                    //connection.close();
                    
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return cr;
    }	
}
