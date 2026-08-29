package com.sunrisedental.dto;

import java.sql.Date;
import java.sql.Time;

public class AppointmentDto {

    private int id;

    // Patient
    private int patientId;
    private String patientName;
    private String contactNo;
    private String address;

    // Appointment
    private String appointmentNo;
    private Date appointmentDate;
    private Time appointmentTime;

    // Dentist
    private int dentistId;
    private String dentistName;
    private double consultation_fee;

    // Treatment
    private int treatmentId;
    private String treatmentName;
    private double treatmentCost;
    private boolean isFinalized;


    public AppointmentDto() {
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


    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }


    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }


    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }


    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }


    public double getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
    }


	public boolean isFinalized() {
		return isFinalized;
	}


	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}


	public double getConsultation_fee() {
		return consultation_fee;
	}


	public void setConsultation_fee(double consultation_fee) {
		this.consultation_fee = consultation_fee;
	}
}