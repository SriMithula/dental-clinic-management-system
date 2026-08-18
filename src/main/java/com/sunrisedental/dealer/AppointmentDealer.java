package com.sunrisedental.dealer;

import java.sql.Date;
import java.sql.Time;

import jakarta.servlet.http.HttpServletRequest;

public class AppointmentDealer {

    public int id = -1;
    public int patientId = -1;
    public String patientName;
    public String contactNo;
    public String address;
    public String appointmentNo;
    public Date appointmentDate;
    public Time appointmentTime;
    public int treatmentId = -1;
    public int dentistId = -1;

    public void fillViaReq(HttpServletRequest request) {

        this.patientName = request.getParameter("patientName");
        this.contactNo = request.getParameter("contactNo");
        this.address = request.getParameter("address");
        this.appointmentNo = request.getParameter("appointmentNo");
        
        String date = request.getParameter("appointmentDate");
        String time = request.getParameter("appointmentTime");
        
        this.appointmentDate = Date.valueOf(date);
        this.appointmentTime = Time.valueOf(time + ":00");

        String patientIdStr = request.getParameter("patientId");
        String dentistStr = request.getParameter("dentistId");
        String treatmentStr = request.getParameter("treatmentId");

        if (patientIdStr != null && !patientIdStr.isEmpty()) {
            this.patientId = Integer.parseInt(patientIdStr);
        }

        if (dentistStr != null && !dentistStr.isEmpty()) {
            this.dentistId = Integer.parseInt(dentistStr);
        }

        if (treatmentStr != null && !treatmentStr.isEmpty()) {
            this.treatmentId = Integer.parseInt(treatmentStr);
        }
    }
}