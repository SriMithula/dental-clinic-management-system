package com.sunrisedental.controller;

import java.io.IOException;
import java.util.List;

import com.sunrisedental.dto.PatientBill;
import com.sunrisedental.service.BillService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillService billService;

    public ReportServlet() {
        super();
        this.billService = new BillService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Check login
        if (session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Get all current bills
        List<PatientBill> patientBills = billService.getAllBills();

        // Send bills to report.jsp
        request.setAttribute("patientBills", patientBills);

        request.getRequestDispatcher("/report.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}