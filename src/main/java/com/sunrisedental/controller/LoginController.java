package com.sunrisedental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sunrisedental.dao.LoginDao;
import com.sunrisedental.dto.UserDto;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao = new LoginDao();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String username = request.getParameter("username");
	     String password = request.getParameter("password");
	     
	     UserDto user = loginDao.login(username, password);
	     
	     if (user != null) {
	            // Create session
	            HttpSession session = request.getSession();
	            
	            session.setAttribute("user", user);
	            session.setAttribute("userId", user.getId());
	            
	            response.sendRedirect(request.getContextPath() + "/dashboard");

	        } else {
	            response.sendRedirect(request.getContextPath() + "/login.jsp?error=true");
	        }
	}

}
