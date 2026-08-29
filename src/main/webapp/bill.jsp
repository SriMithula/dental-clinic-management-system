<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Ensure your Servlet protects the route, but you can keep this session check as a fallback -->
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Bill</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    
	   <link rel="stylesheet" href="./assets/css/bill/bill.css">
</head>
<body>

<div class="bill-container">
    <div class="bill">
        <!-- Clinic Header -->
        <div class="clinic-header">
            <h2>Sunrise Dental Clinic</h2>
            <p class="mb-1">Dental Care & Treatment Center</p>
            <small>Colombo, Sri Lanka</small>
        </div>

        <div class="d-flex justify-content-between mb-4">
            <div>
                <strong>Bill No:</strong>
                <span>B-<%= System.currentTimeMillis() %></span> <!-- Generates a random bill number -->
            </div>
            <div>
                <strong>Date:</strong>
                <span id="billDate"></span>
            </div>
        </div>

        <!-- Patient Information (Populated from Servlet) -->
        <h5 class="mb-3">Patient Information</h5>
        <div class="row g-3 mb-4">
            <div class="col-md-6">
                <label class="fw-bold">Appointment No</label>
                <input type="text" class="form-control" value="${patientBill.appointmentNo}" readonly>
            </div>
            <div class="col-md-6">
                <label class="fw-bold">Patient Name</label>
                <input type="text" class="form-control" value="${patientBill.patientName}" readonly>
            </div>
        </div>

        <!-- Billing (Populated from Servlet) -->
        <h5 class="mb-3">Treatment Details</h5>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Treatment</th>
                        <th width="200" class="text-end">Amount (Rs.)</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${patientBill.treatmentName}</td>
                        <td class="text-end">${String.format("%.2f", patientBill.treatmentFee)}</td>
                    </tr>
                    <tr>
                        <td>Consultation Fee</td>
                        <td class="text-end">${String.format("%.2f", patientBill.consultationFee)}</td>
                    </tr>
                    <tr class="total-row">
                        <td class="text-end">Total</td>
                        <td class="text-end text-primary">
                            Rs. ${String.format("%.2f", (patientBill.consultationFee + patientBill.treatmentFee))}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Buttons -->
        <div class="text-end mt-4 no-print">
            <button type="button" onclick="window.print()" class="btn btn-success">
                <i class="bi bi-printer"></i> Print Bill
            </button>
            <a href="dashboard.jsp" class="btn btn-secondary">Back</a>
        </div>
    </div>
</div>

<script>
    // Set current date
    document.getElementById("billDate").innerText = new Date().toLocaleDateString();
</script>

</body>
</html>