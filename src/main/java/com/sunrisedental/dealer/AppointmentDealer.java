package com.sunrisedental.dealer;

import java.sql.Date;
import java.sql.Time;

import jakarta.servlet.http.HttpServletRequest;

public class AppointmentDealer {

    private int id = -1;
    private int patientId = -1;
    private String patientName;
    private String contactNo;
    private String address;
    private String appointmentNo;
    private Date appointmentDate;
    private Time appointmentTime;
    private int treatmentId = -1;
    private int dentistId = -1;
    
    private AppointmentDealer(Builder builder) {
        this.id = builder.id;
        this.patientId = builder.patientId;
        this.patientName = builder.patientName;
        this.contactNo = builder.contactNo;
        this.address = builder.address;
        this.appointmentNo = builder.appointmentNo;
        this.appointmentDate = builder.appointmentDate;
        this.appointmentTime = builder.appointmentTime;
        this.treatmentId = builder.treatmentId;
        this.dentistId = builder.dentistId;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Time getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public int getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}
	public int getDentistId() {
		return dentistId;
	}
	public void setDentistId(int dentistId) {
		this.dentistId = dentistId;
	}
	
	public static class Builder {
        private int id = -1;
        private int patientId = -1;
        private String patientName;
        private String contactNo;
        private String address;
        private String appointmentNo;
        private Date appointmentDate;
        private Time appointmentTime;
        private int treatmentId = -1;
        private int dentistId = -1;


        public Builder fillViaReq(HttpServletRequest request) {
            this.patientName = request.getParameter("patientName");
            this.contactNo = request.getParameter("contactNo");
            this.address = request.getParameter("address");
            this.appointmentNo = request.getParameter("appointmentNo");

            String date = request.getParameter("appointmentDate");
            String time = request.getParameter("appointmentTime");
            
            if (date != null && !date.isEmpty()) {
                this.appointmentDate = Date.valueOf(date);
            }
            if (time != null && !time.isEmpty()) {
                this.appointmentTime = Time.valueOf(time + ":00");
            }

            String patientIdStr = request.getParameter("patientId");
            if (patientIdStr != null && !patientIdStr.isEmpty()) {
                this.patientId = Integer.parseInt(patientIdStr);
            }

            String dentistStr = request.getParameter("dentistId");
            if (dentistStr != null && !dentistStr.isEmpty()) {
                this.dentistId = Integer.parseInt(dentistStr);
            }

            String treatmentStr = request.getParameter("treatmentId");
            if (treatmentStr != null && !treatmentStr.isEmpty()) {
                this.treatmentId = Integer.parseInt(treatmentStr);
            }
    
            return this; 
        }

        public AppointmentDealer build() {
            return new AppointmentDealer(this);
        }
    }

   
}