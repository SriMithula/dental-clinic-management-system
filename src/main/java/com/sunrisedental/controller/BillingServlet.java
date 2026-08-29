package com.sunrisedental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sunrisedental.dto.PatientBill;
import com.sunrisedental.service.BillService;

@WebServlet("/generateBill")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillService billService;

    public BillingServlet() {
        super();
        this.billService = new BillService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Check login
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get appointment ID
        String appointmentIdParam = request.getParameter("appointmentId");

        if (appointmentIdParam == null || appointmentIdParam.isEmpty()) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Appointment ID is required"
            );
            return;
        }

        int appointmentId;

        try {
            appointmentId = Integer.parseInt(appointmentIdParam);
        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid appointment ID"
            );
            return;
        }

        // Get bill
        PatientBill patientBill =
                billService.getBillByAppointmentId(appointmentId);

        if (patientBill == null) {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Bill not found for appointment ID: " + appointmentId
            );
            return;
        }

        // Send bill to JSP
        request.setAttribute("patientBill", patientBill);

        request.getRequestDispatcher("bill.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}