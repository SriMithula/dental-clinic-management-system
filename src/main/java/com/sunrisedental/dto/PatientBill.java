package com.sunrisedental.dto;

public class PatientBill {
	private String appointmentNo;
    private String patientName;
    private String treatmentName;
    private double treatmentFee;
    private double consultationFee;
    private double totalAmount;
    private int appointmentId;
    private String invoiceId;
    
    public PatientBill(String appointmentNo, String patientName, String treatmentName, double treatmentFee, double consultationFee) {
        this.appointmentNo = appointmentNo;
        this.patientName = patientName;
        this.treatmentName = treatmentName;
        this.treatmentFee = treatmentFee;
        this.consultationFee = consultationFee;
    }
    
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	public double getTreatmentFee() {
		return treatmentFee;
	}
	public void setTreatmentFee(double treatmentFee) {
		this.treatmentFee = treatmentFee;
	}
	public double getConsultationFee() {
		return consultationFee;
	}
	public void setConsultationFee(double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
    
}
