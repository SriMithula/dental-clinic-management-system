package com.sunrisedental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.sunrisedental.dao.PatientDao;
import com.sunrisedental.dto.PatientDto;

/**
 * Servlet implementation class PatientSearchController
 */
@WebServlet("/PatientSearchController")
public class PatientSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PatientDao patientDao = new PatientDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String search = request.getParameter("search");

        if (search == null) {
            search = "";
        }
        
        search = search.trim();

        List<PatientDto> patients = patientDao.searchPatient(search);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        
        response.getWriter().write(gson.toJson(patients));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
