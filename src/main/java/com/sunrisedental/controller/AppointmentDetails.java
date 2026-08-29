package com.sunrisedental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sunrisedental.dto.AppointmentDto;
import com.sunrisedental.dto.CommonResponse;
import com.sunrisedental.service.AppointmentService;

/**
 * Servlet implementation class AppointmentDetails
 */
@WebServlet("/appointmentDetails")
public class AppointmentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AppointmentService appointmentService ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentDetails() {
        super();
        appointmentService = new AppointmentService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AppointmentDto> appointments = new ArrayList<AppointmentDto>();
		
		CommonResponse cr = appointmentService.getAppointments();
		if(cr.status) {
			appointments = (List<AppointmentDto>) cr.extra;
		}

		request.setAttribute("appointments", appointments);

	    request.getRequestDispatcher("/appointment-details.jsp")
	        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
