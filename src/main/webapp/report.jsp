<%@ page import="java.util.List" %>
<%@ page import="com.sunrisedental.dto.PatientBill" %>

<%
if (session.getAttribute("userId") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}

List<PatientBill> patientBills =
    (List<PatientBill>) request.getAttribute("patientBills");

double totalRevenue = 0;

if (patientBills != null) {
    for (PatientBill bill : patientBills) {
        totalRevenue += bill.getTotalAmount();
    }
}

int totalBills = (patientBills != null) ? patientBills.size() : 0;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Billing Report - Sunrise Dental Clinic</title>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
        rel="stylesheet">

    <style>
        body {
            background: #f5f7fb;
            font-family: Arial, sans-serif;
        }

        .report-container {
            max-width: 900px;
            margin: 50px auto;
        }

        .report-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 3px 15px rgba(0, 0, 0, 0.08);
            overflow: hidden;
        }

        .report-header {
            background: #0d6efd;
            color: white;
            padding: 25px;
        }

        .summary-card {
            border: none;
            border-radius: 10px;
            padding: 20px;
            height: 100%;
        }

        .summary-title {
            color: #6c757d;
            font-size: 15px;
        }

        .summary-value {
            font-size: 28px;
            font-weight: bold;
            margin-top: 8px;
        }

        .table th {
            background: #f1f3f5;
        }

        .print-btn {
            margin-top: 20px;
        }

        @media print {
            .no-print {
                display: none !important;
            }

            body {
                background: white;
            }

            .report-container {
                margin: 0;
                max-width: 100%;
            }

            .report-card {
                box-shadow: none;
            }
        }
    </style>
</head>

<body>

<div class="report-container">

    <div class="report-card">

        <div class="report-header">
            <h2 class="mb-1">
                <i class="bi bi-bar-chart-line"></i>
                Billing Report
            </h2>

            <p class="mb-0">
                Sunrise Dental Clinic
            </p>
        </div>

        <div class="p-4">

            <!-- Summary -->
            <div class="row g-4 mb-4">

                <div class="col-md-6">
                    <div class="summary-card bg-light">
                        <div class="summary-title">
                            Total Generated Bills
                        </div>

                        <div class="summary-value text-primary">
                            <%= totalBills %>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="summary-card bg-light">
                        <div class="summary-title">
                            Total Revenue
                        </div>

                        <div class="summary-value text-success">
                            Rs. <%= String.format("%.2f", totalRevenue) %>
                        </div>
                    </div>
                </div>

            </div>

            <!-- Bills Table -->
            <h5 class="mb-3">Generated Bills</h5>

            <div class="table-responsive">

                <table class="table table-bordered align-middle">

                    <thead>
                        <tr>
                            <th>Invoice No</th>
                            <th>Appointment No</th>
                            <th>Patient</th>
                            <th>Treatment</th>
                            <th>Total (Rs.)</th>
                        </tr>
                    </thead>

                    <tbody>

                    <%
                    if (patientBills != null && !patientBills.isEmpty()) {

                        for (PatientBill bill : patientBills) {
                    %>

                        <tr>
                            <td><%= bill.getInvoiceId() %></td>

                            <td><%= bill.getAppointmentNo() %></td>

                            <td><%= bill.getPatientName() %></td>

                            <td><%= bill.getTreatmentName() %></td>

                            <td>
                                <%= String.format("%.2f", bill.getTotalAmount()) %>
                            </td>
                        </tr>

                    <%
                        }

                    } else {
                    %>

                        <tr>
                            <td colspan="5" class="text-center">
                                No bills available.
                            </td>
                        </tr>

                    <%
                    }
                    %>

                    </tbody>

                </table>

            </div>

            <!-- Buttons -->
            <div class="text-end no-print">

                <button
                    type="button"
                    class="btn btn-success print-btn"
                    onclick="window.print()">

                    <i class="bi bi-printer"></i>
                    Print Report

                </button>

                <a
                    href="<%= request.getContextPath() %>/bill"
                    class="btn btn-secondary print-btn">

                    <i class="bi bi-arrow-left"></i>
                    Back

                </a>

            </div>

        </div>

    </div>

</div>

</body>
</html>