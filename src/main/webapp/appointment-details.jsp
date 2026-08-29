<%@ page import="com.sunrisedental.dto.AppointmentDto" %>
<%@ page import="java.util.List" %>

<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%

    // ==========================
    // LOGIN CHECK
    // ==========================

    if (session.getAttribute("userId") == null) {

        response.sendRedirect(
            request.getContextPath() +
            "/login.jsp"
        );

        return;
    }

    List<AppointmentDto> appointments =
        (List<AppointmentDto>)
        request.getAttribute("appointments");

%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        Appointments - Sunrise Dental Clinic
    </title>


    <!-- Bootstrap -->

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">


    <!-- Bootstrap Icons -->

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
        rel="stylesheet">


    <!-- DataTables -->

    <link
        rel="stylesheet"
        href="https://cdn.datatables.net/2.3.2/css/dataTables.bootstrap5.min.css">


    <!-- Custom CSS -->

    <link
        rel="stylesheet"
        href="<%= request.getContextPath() %>/assets/css/appointment/appointment-details.css">


    <style>

        .appointment-table-wrapper {
            width: 100%;
        }

        .patient-icon {

            width: 32px;
            height: 32px;

            display: inline-flex;

            align-items: center;
            justify-content: center;

            border-radius: 50%;

            background: #e9f2ff;
            color: #0d6efd;
        }

        #appointmentTable th {
            white-space: nowrap;
        }

        #appointmentTable td {
            vertical-align: middle;
        }

        .appointment-number {

            font-weight: 600;

            color: #0d6efd;
        }

        .action-buttons {

            display: flex;

            gap: 5px;
        }

        .action-buttons .btn {

            width: 34px;
            height: 34px;

            display: flex;

            align-items: center;
            justify-content: center;
        }

        .dt-search input {
            margin-left: 8px;
        }

        .dt-length select {
            margin: 0 5px;
        }

        .hide-pagination .dt-paging {
            display: none !important;
        }

        @media (max-width: 768px) {

            .card-header-custom .btn {
                font-size: 13px;
            }

            #appointmentTable {
                font-size: 13px;
            }
        }

    </style>

</head>


<body>


<div class="page-container">

    <div class="card">


        <!-- ==========================
             HEADER
        =========================== -->

        <div class="card-header-custom">

            <div class="d-flex justify-content-between align-items-center">

                <div>

                    <h4 class="mb-1">

                        <i class="bi bi-calendar-event"></i>

                        Appointments

                    </h4>

                    <small>
                        Sunrise Dental Clinic
                    </small>

                </div>


                <div class="d-flex gap-2">

                    <!-- New Appointment -->

                    <a
                        href="<%= request.getContextPath() %>/appointment?action=CREATE"
                        class="btn btn-light">

                        <i class="bi bi-plus-circle"></i>

                        New Appointment

                    </a>


                    <!-- Dashboard -->

                    <a
                        href="<%= request.getContextPath() %>/dashboard"
                        class="btn btn-light">

                        <i class="bi bi-house"></i>

                        Dashboard

                    </a>

                </div>

            </div>

        </div>


        <!-- ==========================
             CONTENT
        =========================== -->

        <div class="p-4">

            <div class="appointment-table-wrapper">


                <!-- ==========================
                     APPOINTMENT TABLE
                =========================== -->

                <table
                    id="appointmentTable"
                    class="table table-hover align-middle"
                    style="width:100%">


                    <thead class="table-light">

                        <tr>

                            <th>#</th>

                            <th>
                                Appointment No
                            </th>

                            <th>
                                Patient
                            </th>

                            <th>
                                Contact
                            </th>

                            <th>
                                Dentist
                            </th>

                            <th>
                                Treatment
                            </th>

                            <th>
                                Date
                            </th>

                            <th>
                                Time
                            </th>

                            <th>
                                Actions
                            </th>

                        </tr>

                    </thead>


                    <tbody>

                    <%

                        if (appointments != null &&
                            !appointments.isEmpty()) {

                            int rowNo = 1;

                            for (AppointmentDto appointment :
                                 appointments) {

                    %>

                    <tr>


                        <!-- Row -->

                        <td>
                            <%= rowNo++ %>
                        </td>


                        <!-- Appointment No -->

                        <td>

                            <span class="appointment-number">

                                <%= appointment.getAppointmentNo() %>

                            </span>

                        </td>


                        <!-- Patient -->

                        <td>

                            <div class="d-flex align-items-center gap-2">

                                <span class="patient-icon">

                                    <i class="bi bi-person"></i>

                                </span>

                                <span>

                                    <%= appointment.getPatientName() %>

                                </span>

                            </div>

                        </td>


                        <!-- Contact -->

                        <td>

                            <%= appointment.getContactNo() %>

                        </td>


                        <!-- Dentist -->

                        <td>

                            <i class="bi bi-person-badge text-primary"></i>

                            <%= appointment.getDentistName() %>

                        </td>


                        <!-- Treatment -->

                        <td>

                            <%= appointment.getTreatmentName() %>

                        </td>


                        <!-- Date -->

                        <td>

                            <i class="bi bi-calendar3"></i>

                            <%= appointment.getAppointmentDate() %>

                        </td>


                        <!-- Time -->

                        <td>

                            <i class="bi bi-clock"></i>

                            <%= appointment.getAppointmentTime() %>

                        </td>


                        <!-- Actions -->

                        <td>

                            <div class="action-buttons">


                                <!-- VIEW -->

                                <!--- <a
                                    href="<%= request.getContextPath() %>/appointment?action=VIEW&id=<%= appointment.getId() %>"
                                    class="btn btn-sm btn-outline-primary"
                                    title="View Appointment">

                                    <i class="bi bi-eye"></i>

                                </a> --->
                                
                                <%
								    boolean isFinalized = appointment.isFinalized();
								%>
								
								<% if (!isFinalized) { %>
								
								    <!-- FINALIZE -->
								    <a
								        href="<%= request.getContextPath() %>/appointment?action=FINALIZE&id=<%= appointment.getId() %>"
								        class="btn btn-sm btn-outline-success"
								        title="Finalize Appointment"
								        onclick="return confirm('Are you sure you want to finalize this appointment?');">
								
								        <i class="bi bi-clipboard2-check"></i>
								
								    </a>
								
								<% } else { %>
								
								    <!-- ALREADY FINALIZED -->
								    <button
								        type="button"
								        class="btn btn-sm btn-outline-secondary"
								        title="Appointment Finalized"
								        disabled>
								
								        <i class="bi bi-check2-all"></i>
								
								    </button>
								
								<% } %>
                                


                                <!-- EDIT -->

                             	<!-- <a
                                    href="<%= request.getContextPath() %>/appointment?action=EDIT&id=<%= appointment.getId() %>"
                                    class="btn btn-sm btn-outline-warning"
                                    title="Edit Appointment">

                                    <i class="bi bi-pencil"></i>

                                </a> -->


                                <!-- DELETE -->

                                <!--  <a
                                    href="<%= request.getContextPath() %>/appointment?action=DELETE&id=<%= appointment.getId() %>"
                                    class="btn btn-sm btn-outline-danger"
                                    title="Delete Appointment"

                                    onclick="return confirm('Are you sure you want to delete this appointment?');">

                                    <i class="bi bi-trash"></i>

                                </a> --->


                            </div>

                        </td>


                    </tr>


                    <%

                            }

                        }

                    %>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>



<!-- ==========================
     JAVASCRIPT
=========================== -->


<!-- jQuery -->

<script
    src="https://code.jquery.com/jquery-3.7.1.min.js">
</script>


<!-- Bootstrap -->

<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>


<!-- DataTables -->

<script
    src="https://cdn.datatables.net/2.3.2/js/dataTables.min.js">
</script>


<script
    src="https://cdn.datatables.net/2.3.2/js/dataTables.bootstrap5.min.js">
</script>


<script>

$(document).ready(function () {


    // ==========================
    // DATATABLE
    // ==========================

    const table =
        $('#appointmentTable').DataTable({

        	 pageLength: 7,

       	    lengthMenu: [
       	        [7, 10, 25, 50, 100],
       	        [7, 10, 25, 50, 100]
       	    ],

            searching: true,

            ordering: true,

            paging: true,

            info: true,

            autoWidth: false,

            language: {

                search: "Search:",

                searchPlaceholder:
                    "Search appointments...",

                lengthMenu:
                    "Show _MENU_ appointments",

                info:
                    "Showing _START_ to _END_ of _TOTAL_ appointments",

                infoEmpty:
                    "No appointments available",

                zeroRecords:
                    "No matching appointments found",

                emptyTable:
                    "No appointments available"
            },

            columnDefs: [

                {
                    targets: 0,

                    searchable: false,

                    orderable: false
                },

                {
                    targets: 8,

                    searchable: false,

                    orderable: false
                }

            ]

        });


    // ==========================
    // HIDE PAGINATION
    // ==========================

    function updatePagination() {

        const pageInfo =
            table.page.info();

        if (pageInfo.pages <= 1) {

            $('#appointmentTable_wrapper')
                .addClass('hide-pagination');

        } else {

            $('#appointmentTable_wrapper')
                .removeClass('hide-pagination');
        }
    }


    updatePagination();


    table.on(
        'draw',
        function () {

            updatePagination();

        }
    );

});

</script>


</body>

</html>