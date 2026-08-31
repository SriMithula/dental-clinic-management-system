<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ page import="com.sunrisedental.dto.DashboardDto" %>
<%@ page import="com.sunrisedental.dto.UserDto" %>
<% DashboardDto dashboard = (DashboardDto) request.getAttribute("dashboard");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Sunrise Dental Clinic - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="./assets/css/dashboard/dashboard.css">
</head>

<body>

<div class="sidebar">

    <h4>
        <i class="bi bi-hospital"></i>
        Sunrise Dental
    </h4>

    <a  href="<%= request.getContextPath() %>/dashboard">
        <i class="bi bi-speedometer2"></i>
        Dashboard
    </a>

    <a href="<%= request.getContextPath() %>/appointment">
	    <i class="bi bi-calendar-plus"></i>
	    Appointment
	</a>

    <a  href="<%= request.getContextPath() %>/appointmentDetails">
        <i class="bi bi-search"></i>
        Appointment Details
    </a>
    
    <a href="<%= request.getContextPath() %>/patients.jsp">
    	<i class="bi bi-people"></i>
    	Patient Details
	</a>

    <a href="<%= request.getContextPath() %>/bill">
        <i class="bi bi-receipt"></i>
        Billing
    </a>

    <a href="<%= request.getContextPath() %>/help.jsp">
    	<i class="bi bi-question-circle"></i>
    	Help
	</a>

 
	<a href="<%= request.getContextPath() %>/logout">
        <i class="bi bi-box-arrow-right"></i>
        Logout
    </a>

</div>


<div class="main">

    <div class="topbar d-flex justify-content-between align-items-center">

        <div>
            <h4 class="mb-1">Dashboard</h4>
            <small class="text-muted">
                Sunrise Dental Clinic Management System
            </small>
        </div>

        <div>
            <i class="bi bi-person-circle"></i>
            <%
            	UserDto user = (UserDto) session.getAttribute("user");
                if (user != null) {
            %>
               <%= user.getUsername() %>
            <%
                }
            %>
        </div>

    </div>



    <div class="row g-4">

        <div class="col-md-3">

            <div class="card p-3"
     onclick="window.location.href='<%= request.getContextPath() %>/appointmentDetails';"
     style="cursor: pointer;">

                <div class="d-flex justify-content-between">

                    <div>
                        <p class="text-muted mb-1">
                            Appointments
                        </p>

                       <h3><%= dashboard.getTotalAppointments() %></h3>
                    </div>

                    <div class="stat-icon blue">
                        <i class="bi bi-calendar-check"></i>
                    </div>

                </div>

            </div>

        </div>


        <div class="col-md-3">

            <div class="card p-3"
     onclick="window.location.href='<%= request.getContextPath() %>/patients.jsp';"
     style="cursor: pointer;">

                <div class="d-flex justify-content-between">

                    <div>
                        <p class="text-muted mb-1">
                            Patients
                        </p>

               			<h3><%= dashboard.getTotalPatients() %></h3>
                    </div>

                    <div class="stat-icon green">
                        <i class="bi bi-people"></i>
                    </div>

                </div>

            </div>

        </div>


        <div class="col-md-3">

            <div class="card p-3">

                <div class="d-flex justify-content-between">

                    <div>
                        <p class="text-muted mb-1">
                            Today's Visits
                        </p>

           				<h3><%= dashboard.getTodayVisits() %></h3>
                    </div>

                    <div class="stat-icon orange">
                        <i class="bi bi-person-check"></i>
                    </div>

                </div>

            </div>

        </div>


        <div class="col-md-3">

            <div class="card p-3">

                <div class="d-flex justify-content-between">

                    <div>
                        <p class="text-muted mb-1">
                            Revenue
                        </p>

             			<h3>
						    Rs. <%= String.format("%,.2f", dashboard.getRevenue()) %>
						</h3>
                    </div>

                    <div class="stat-icon purple">
                        <i class="bi bi-cash-stack"></i>
                    </div>

                </div>

            </div>

        </div>

    </div>



    <div class="card mt-4 p-4">

        <h5 class="mb-4">
            Quick Actions
        </h5>

        <div class="row g-3">

           <div class="col-md-6">

                <a href="<%= request.getContextPath() %>/appointment"
                   class="btn btn-primary w-100 py-3">

                    <i class="bi bi-calendar-plus"></i>
                    Register Appointment

                </a>

            </div>

            <div class="col-md-6">

                <a href="<%= request.getContextPath() %>/appointmentDetails"
                   class="btn btn-outline-primary w-100 py-3">

                    <i class="bi bi-search"></i>
                    Search Appointment

                </a>

            </div>
            
        </div>

    </div>

</div>
</body>
</html>