package com.sunrisedental.dao;

import java.util.List;

import com.sunrisedental.dto.PatientBill;

public interface InvoiceDao {
	  int createInvoice(int appointmentId, double treatmentCost, double consultation_fee);
	  PatientBill getInvoiceByAppointmentId(int appointmentId);
	  List<PatientBill> getAllBills();
}