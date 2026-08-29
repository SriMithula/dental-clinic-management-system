package com.sunrisedental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.dto.CommonResponse;
import com.sunrisedental.enums.Action;
import com.sunrisedental.service.AppointmentService;

/**
 * Servlet implementation class AppointmentController
 */
@WebServlet("/appointment")
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentService appointmentService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentController() {
        super();
        // TODO Auto-generated constructor stub
        this.appointmentService = new AppointmentService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
			
		if (action != null && action.equalsIgnoreCase("FINALIZE")) {

	        String idParam = request.getParameter("id");
	
	        if (idParam == null || idParam.isEmpty()) {
	            response.sendRedirect(request.getContextPath()+ "/appointment");
	            return;
	        }

            try {

                int appointmentId = Integer.parseInt(idParam);

                CommonResponse cr = appointmentService.finalizeAppointment(appointmentId);

                if (cr.status) {
                    response.sendRedirect(
                            request.getContextPath()
                                    + "/generateBill?"
                                    + "&appointmentId="
                                    + appointmentId
                    );

                } else {
                    request.setAttribute("error",cr.error);
                }

            } catch (NumberFormatException e) {

                request.setAttribute(
                        "error",
                        "Invalid appointment ID."
                );
            }

            return;
	    }

	    request.setAttribute("dentists",appointmentService.getDentists());
	    request.setAttribute("treatments",appointmentService.getTreatments());
		
	    request.getRequestDispatcher("/appointment.jsp")
	        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

        Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );
            return;
        }
		
		if (action.equals(Action.CREATE.toString())) {
			AppointmentDealer dealer = new AppointmentDealer.Builder()
                    .fillViaReq(request)
                    .build();
			
			CommonResponse cr =  this.appointmentService.saveAppointment(dealer, userId);
			
			if(cr.status) {
			    request.setAttribute("dentists",appointmentService.getDentists());
			    request.setAttribute("treatments",appointmentService.getTreatments());
			    response.sendRedirect(request.getContextPath() + "/appointment?success=true");
			}else {
			    request.setAttribute("error", cr.error);
			    request.setAttribute("treatments",appointmentService.getTreatments());
			    request.setAttribute("dentists",appointmentService.getDentists());
			    request.getRequestDispatcher("/appointment.jsp")
                .forward(request, response);
			}
		}
		
	}

}
