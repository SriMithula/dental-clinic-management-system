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

/**
 * Serves the Billing list page (invoice.jsp) -- shows every bill that has
 * been generated so far. This is separate from BillingServlet
 * (/generateBill), which loads and prints a single bill right after an
 * appointment is finalized; both end up reusing BillService/InvoiceDao,
 * just for a list vs. a single record.
 */
@WebServlet("/bill")
public class BillingListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BillService billService;

    public BillingListServlet() {
        super();
        this.billService = new BillService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<PatientBill> patientBills = billService.getAllBills();

        request.setAttribute("patientBills", patientBills);

        request.getRequestDispatcher("/invoice.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}