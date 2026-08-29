package com.sunrisedental.service;

import com.sunrisedental.dao.InvoiceDao;
import com.sunrisedental.dao.impl.InvoiceDaoImpl;
import com.sunrisedental.dto.PatientBill;

public class BillService {

    private final InvoiceDao invoiceDao;

    public BillService() {
        this.invoiceDao = new InvoiceDaoImpl();
    }

    public PatientBill getBillByAppointmentId(int appointmentId) {
        return invoiceDao.getInvoiceByAppointmentId(appointmentId);
    }
}