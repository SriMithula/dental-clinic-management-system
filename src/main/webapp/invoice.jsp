<%@ page import="com.sunrisedental.dto.PatientBill" %>
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
            request.getContextPath() + "/login.jsp"
        );

        return;
    }

    List<PatientBill> patientBills =
        (List<PatientBill>) request.getAttribute("patientBills");
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        Bills - Sunrise Dental Clinic
    </title>


    <!-- ==========================
         BOOTSTRAP
    =========================== -->

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">


    <!-- ==========================
         BOOTSTRAP ICONS
    =========================== -->

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
        rel="stylesheet">


    <!-- ==========================
         DATATABLES
    =========================== -->

    <link
        rel="stylesheet"
        href="https://cdn.datatables.net/2.3.2/css/dataTables.bootstrap5.min.css">


    <!-- ==========================
         CUSTOM CSS
    =========================== -->

    <style>

        body {
            background-color: #f5f7fb;
            font-family: Arial, sans-serif;
        }

        .page-container {
            padding: 30px;
        }

        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
            overflow: hidden;
        }

        /* ==========================
           HEADER
        =========================== */

        .card-header-custom {
            background: #0d6efd;
            color: white;
            padding: 20px 24px;
        }

        .card-header-custom h4 {
            margin: 0;
            font-weight: 600;
        }

        .card-header-custom small {
            opacity: 0.85;
        }

        .card-header-custom .btn {
            border-radius: 6px;
        }

        /* ==========================
           TABLE
        =========================== */

        .bill-table-wrapper {
            width: 100%;
            overflow-x: auto;
        }

        #billTable {
            width: 100% !important;
        }

        #billTable th {
            white-space: nowrap;
            vertical-align: middle;
        }

        #billTable td {
            vertical-align: middle;
        }

        .invoice-number {
            font-weight: 600;
            color: #0d6efd;
        }

        .appointment-number {
            font-weight: 600;
            color: #198754;
        }

        .patient-icon {
            width: 34px;
            height: 34px;

            display: inline-flex;

            align-items: center;
            justify-content: center;

            border-radius: 50%;

            background: #e9f2ff;
            color: #0d6efd;
        }

        .amount {
            font-weight: 600;
            white-space: nowrap;
        }

        .total-amount {
            font-weight: 700;
            color: #198754;
            white-space: nowrap;
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

        /* ==========================
           DATATABLE
        =========================== */

        .dt-search input {
            margin-left: 8px;
        }

        .dt-length select {
            margin: 0 5px;
        }

        .hide-pagination .dt-paging {
            display: none !important;
        }

        /* ==========================
           EMPTY
        =========================== */

        .empty-message {
            text-align: center;
            padding: 50px 20px;
            color: #6c757d;
        }

        .empty-message i {
            font-size: 45px;
            margin-bottom: 15px;
            display: block;
        }

        /* ==========================
           MOBILE
        =========================== */

        @media (max-width: 768px) {

            .page-container {
                padding: 15px;
            }

            .card-header-custom {
                padding: 15px;
            }

            .card-header-custom h4 {
                font-size: 18px;
            }

            .card-header-custom .btn {
                font-size: 13px;
            }

            #billTable {
                font-size: 13px;
            }

            .patient-icon {
                width: 30px;
                height: 30px;
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

                        <i class="bi bi-receipt"></i>

                        Bills

                    </h4>

                    <small>
                        Sunrise Dental Clinic
                    </small>

                </div>


                <div class="d-flex gap-2">

                    <!-- Appointments -->

                    <a
                        href="<%= request.getContextPath() %>/appointment"
                        class="btn btn-light">

                        <i class="bi bi-calendar-event"></i>

                        Appointments

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

            <div class="bill-table-wrapper">


                <!-- ==========================
                     BILL TABLE
                =========================== -->

                <table
                    id="billTable"
                    class="table table-hover align-middle">


                    <thead class="table-light">

                        <tr>

                            <th>
                                #
                            </th>

                            <th>
                                Invoice No
                            </th>

                            <th>
                                Appointment No
                            </th>

                            <th>
                                Patient
                            </th>

                            <th>
                                Treatment
                            </th>

                            <th>
                                Treatment Fee
                            </th>

                            <th>
                                Consultation Fee
                            </th>

                            <th>
                                Total
                            </th>

                            <th>
                                Actions
                            </th>

                        </tr>

                    </thead>


                    <tbody>

                    <%

                        if (patientBills != null &&
                            !patientBills.isEmpty()) {

                            int rowNo = 1;

                            for (PatientBill bill :
                                 patientBills) {

                    %>

                        <tr>


                            <!-- ROW NUMBER -->

                            <td>

                                <%= rowNo++ %>

                            </td>


                            <!-- INVOICE -->

                            <td>

                                <span class="invoice-number">

                                    <i class="bi bi-receipt"></i>

                                    #<%= bill.getInvoiceId() %>

                                </span>

                            </td>


                            <!-- APPOINTMENT -->

                            <td>

                                <span class="appointment-number">

                                    <%= bill.getAppointmentNo() %>

                                </span>

                            </td>


                            <!-- PATIENT -->

                            <td>

                                <div
                                    class="d-flex align-items-center gap-2">

                                    <span class="patient-icon">

                                        <i class="bi bi-person"></i>

                                    </span>

                                    <span>

                                        <%= bill.getPatientName() %>

                                    </span>

                                </div>

                            </td>


                            <!-- TREATMENT -->

                            <td>

                                <i
                                    class="bi bi-clipboard2-pulse text-primary">
                                </i>

                                <%= bill.getTreatmentName() %>

                            </td>


                            <!-- TREATMENT FEE -->

                            <td>

                                <span class="amount">

                                    Rs.
                                    <%= String.format(
                                        "%.2f",
                                        bill.getTreatmentFee()
                                    ) %>

                                </span>

                            </td>


                            <!-- CONSULTATION FEE -->

                            <td>

                                <span class="amount">

                                    Rs.
                                    <%= String.format(
                                        "%.2f",
                                        bill.getConsultationFee()
                                    ) %>

                                </span>

                            </td>


                            <!-- TOTAL -->

                            <td>

                                <span class="total-amount">

                                    Rs.
                                    <%= String.format(
                                        "%.2f",
                                        bill.getTotalAmount()
                                    ) %>

                                </span>

                            </td>


                            <!-- ACTION -->

                            <td>

                                <div class="action-buttons">


                                    <!-- VIEW BILL -->

                                    <a
                                        href="<%= request.getContextPath() %>/generateBill?appointmentId=<%= bill.getAppointmentId() %>&source=billing"
                                        class="btn btn-sm btn-outline-primary"
                                        title="View Bill">

                                        <i class="bi bi-eye"></i>

                                    </a>


                                    <!-- PRINT -->

                                    <a
                                        href="<%= request.getContextPath() %>/generateBill?appointmentId=<%= bill.getAppointmentId() %>&print=true&source=billing"
                                        class="btn btn-sm btn-outline-secondary"
                                        title="Print Bill">

                                        <i class="bi bi-printer"></i>

                                    </a>

                                </div>

                            </td>


                        </tr>


                    <%

                            }

                        }

                    %>

                    </tbody>

                </table>


                <%

                    if (patientBills == null ||
                        patientBills.isEmpty()) {

                %>

                    <div class="empty-message">

                        <i class="bi bi-receipt"></i>

                        <h5>
                            No Bills Available
                        </h5>

                        <p class="mb-0">
                            No invoices have been generated yet.
                        </p>

                    </div>

                <%

                    }

                %>

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

    const table = $('#billTable').DataTable({

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
                "Search bills...",

            lengthMenu:
                "Show _MENU_ bills",

            info:
                "Showing _START_ to _END_ of _TOTAL_ bills",

            infoEmpty:
                "No bills available",

            zeroRecords:
                "No matching bills found",

            emptyTable:
                "No bills available"

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
    // WHEN ONLY ONE PAGE
    // ==========================

    function updatePagination() {

        const pageInfo = table.page.info();

        if (pageInfo.pages <= 1) {

            $('#billTable_wrapper')
                .addClass('hide-pagination');

        } else {

            $('#billTable_wrapper')
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