<%@ page import="com.sunrisedental.dto.TreatmentDto" %>
<%@ page import="com.sunrisedental.dto.DentistDto" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Prevent accessing appointment page without login
    if (session.getAttribute("userId") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    // Get dropdown data
    List<DentistDto> dentists = (List<DentistDto>) request.getAttribute("dentists");

    List<TreatmentDto> treatments = (List<TreatmentDto>) request.getAttribute("treatments");

    // Error message comes from forward()
    String error = (String) request.getAttribute("error");

    // Success comes from redirect URL: ?success=true
    String success = request.getParameter("success");

    // Preserve submitted values when there is an error
    String patientId = request.getParameter("patientId");
    String patientName = request.getParameter("patientName");
    String contactNo = request.getParameter("contactNo");
    String address = request.getParameter("address");
    String appointmentNo = request.getParameter("appointmentNo");
    String dentistId = request.getParameter("dentistId");
    String treatmentId = request.getParameter("treatmentId");
    String appointmentDate = request.getParameter("appointmentDate");
    String appointmentTime = request.getParameter("appointmentTime");

    // Avoid null values
    patientId = patientId != null ? patientId : "";
    patientName = patientName != null ? patientName : "";
    contactNo = contactNo != null ? contactNo : "";
    address = address != null ? address : "";
    appointmentNo = appointmentNo != null ? appointmentNo : "";
    dentistId = dentistId != null ? dentistId : "";
    treatmentId = treatmentId != null ? treatmentId : "";
    appointmentDate = appointmentDate != null ? appointmentDate : "";
    appointmentTime = appointmentTime != null ? appointmentTime : "";
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Appointment - Sunrise Dental Clinic</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/appointment/appointment.css">
</head>

<body>

<div class="page-container">

    <div class="card">

        <div class="card-header-custom">

            <div class="d-flex justify-content-between align-items-center">

                <div>

                    <h4>
                        <i class="bi bi-calendar-plus"></i>
                        Register Appointment
                    </h4>

                    <small>
                        Sunrise Dental Clinic
                    </small>

                </div>

                <a href="<%= request.getContextPath() %>/dashboard"
                   class="btn btn-light">

                    <i class="bi bi-house"></i>
                    Dashboard

                </a>

            </div>

        </div>


        <form
            action="<%= request.getContextPath() %>/appointment"
            method="post"
            id="appointmentForm" novalidate>


            <input type="hidden"
                   name="action"
                   value="CREATE">


            <% if ("true".equals(success)) { %>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle"></i>
                    Appointment created successfully.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            <% } %>


            <% if (error != null && !error.trim().isEmpty()) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-circle"></i>
                    <%= error %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            <% } %>


            <div class="p-4">


                <div class="section-title">

                    <i class="bi bi-person"></i>

                    Patient Information

                </div>



                <div class="patient-search-box mb-4">

                    <label>
                        Search Existing Patient
                    </label>


                    <div class="input-group">

                        <input
                            type="text"
                            id="patientSearch"
                            class="form-control"
                            placeholder="Enter patient name or contact number">


                        <button
                            type="button"
                            class="btn btn-primary"
                            onclick="searchPatient()">

                            <i class="bi bi-search"></i>

                            Search

                        </button>

                    </div>


                    <small class="text-muted">

                        Search for an existing patient before
                        registering a new patient.

                    </small>


                    <div
                        id="patientResults"
                        class="list-group patient-results mt-3">
                    </div>



                    <div
                        id="selectedPatient"
                        class="selected-patient mt-3">

                        <div class="d-flex justify-content-between">

                            <div>

                                <strong>

                                    <i class="bi bi-check-circle text-success"></i>

                                    Patient Selected

                                </strong>

                                <div
                                    id="selectedPatientName"
                                    class="mt-1">
                                </div>

                            </div>


                            <button
                                type="button"
                                class="btn btn-sm btn-outline-danger"
                                onclick="clearPatient()">

                                Change

                            </button>

                        </div>

                    </div>



                    <button
                        type="button"
                        class="btn btn-outline-success new-patient-btn"
                        onclick="newPatient()">

                        <i class="bi bi-person-plus"></i>

                        Register New Patient

                    </button>

                </div>


                <input
                    type="hidden"
                    name="patientId"
                    id="patientId"
                    value="<%= patientId %>">


                <div class="row g-3">


                    <!-- Patient Name -->

                    <div class="col-md-6">

                        <label for="patientName">

                            Patient Name

                            <span class="required">*</span>

                        </label>

                        <input
                            type="text"
                            name="patientName"
                            id="patientName"
                            class="form-control"
                            placeholder="Enter patient name"
                            value="<%= patientName %>"
                            >

                    </div>


                    <!-- Contact -->

                    <div class="col-md-6">

                        <label for="contactNo">

                            Contact Number

                            <span class="required">*</span>

                        </label>

                        <input
                            type="tel"
                            name="contactNo"
                            id="contactNo"
                            class="form-control"
                            placeholder="07XXXXXXXX"
                            value="<%= contactNo %>"
                            >

                        <small class="text-muted">

                            Example: 0712345678

                        </small>

                    </div>


                    <!-- Address -->

                    <div class="col-md-12">

                        <label for="address">

                            Address

                            <span class="required">*</span>

                        </label>

                        <textarea
                            name="address"
                            id="address"
                            class="form-control"
                            placeholder="Enter patient address"
                            ><%= address %></textarea>

                    </div>

                </div>


                <div class="appointment-section">

                    <div class="section-title">

                        <i class="bi bi-calendar-event"></i>

                        Appointment Information

                    </div>


                    <div class="row g-3">


                        <!-- Appointment Number -->

                        <div class="col-md-6">

                            <label for="appointmentNo">

                                Appointment Number

                                <span class="required">*</span>

                            </label>

                            <input
                                type="text"
                                name="appointmentNo"
                                id="appointmentNo"
                                class="form-control"
                                placeholder="Example: APT001"
                                value="<%= appointmentNo %>"
                                >

                        </div>



                        <div class="col-md-6">

                            <label for="dentist">

                                Dentist

                                <span class="required">*</span>

                            </label>

                            <select
                                name="dentistId"
                                id="dentist"
                                class="form-select"
                                >

                                <option value="">
                                    -- Select Dentist --
                                </option>


                                <%
                                    if (dentists != null) {

                                        for (DentistDto dentist : dentists) {

                                            boolean selected =
                                                String.valueOf(dentist.getId())
                                                .equals(dentistId);
                                %>

                                    <option
                                        value="<%= dentist.getId() %>"
                                        <%= selected ? "selected" : "" %>>

                                        <%= dentist.getName() %>

                                    </option>

                                <%
                                        }
                                    }
                                %>

                            </select>

                        </div>


                        <div class="col-md-6">

                            <label for="treatmentType">

                                Treatment Type

                                <span class="required">*</span>

                            </label>

                            <select
                                name="treatmentId"
                                id="treatmentType"
                                class="form-select"
                                >

                                <option value="">
                                    -- Select Treatment --
                                </option>


                                <%
                                    if (treatments != null) {

                                        for (TreatmentDto treatment : treatments) {

                                            boolean selected =
                                                String.valueOf(treatment.getId())
                                                .equals(treatmentId);
                                %>

                                    <option
                                        value="<%= treatment.getId() %>"
                                        <%= selected ? "selected" : "" %>>

                                        <%= treatment.getName() %>
                                        - Rs. <%= treatment.getCost() %>

                                    </option>

                                <%
                                        }
                                    }
                                %>

                            </select>

                        </div>


                        <div class="col-md-3">

                            <label for="appointmentDate">

                                Appointment Date

                                <span class="required">*</span>

                            </label>

                            <input
                                type="date"
                                name="appointmentDate"
                                id="appointmentDate"
                                class="form-control"
                                value="<%= appointmentDate %>"
                                >

                        </div>


                        <div class="col-md-3">

                            <label for="appointmentTime">

                                Appointment Time

                                <span class="required">*</span>

                            </label>

                            <input
                                type="time"
                                name="appointmentTime"
                                id="appointmentTime"
                                class="form-control"
                                value="<%= appointmentTime %>"
                                >

                        </div>

                    </div>

                </div>


                <div class="d-flex justify-content-end gap-2 mt-4">


                    <!-- Reset -->

                    <button
                        type="reset"
                        class="btn btn-secondary"
                        onclick="clearPatient()">

                        <i class="bi bi-arrow-counterclockwise"></i>

                        Reset

                    </button>


                    <!-- Cancel -->

                    <a
                        href="<%= request.getContextPath() %>/dashboard"
                        class="btn btn-outline-dark">

                        Close

                    </a>


                    <!-- Save -->

                    <button
                        type="submit"
                        class="btn btn-primary">

                        <i class="bi bi-save"></i>

                        Save Appointment

                    </button>

                </div>

            </div>

        </form>

    </div>

</div>


<script src="<%= request.getContextPath() %>/assets/js/appointment/appointment.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
