<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Patient Bill</title>

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

        .bill-container {
            max-width: 850px;
            margin: 40px auto;
        }

        .bill {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            padding: 35px;
        }

        .clinic-header {
            text-align: center;
            border-bottom: 2px solid #0d6efd;
            padding-bottom: 20px;
            margin-bottom: 25px;
        }

        .clinic-header h2 {
            color: #0d6efd;
            margin-bottom: 5px;
        }

        .total-row {
            font-size: 20px;
            font-weight: bold;
        }

        @media print {

            body {
                background: white;
            }

            .no-print {
                display: none !important;
            }

            .bill-container {
                margin: 0;
                max-width: 100%;
            }

            .bill {
                box-shadow: none;
            }

        }

    </style>

</head>

<body>

<div class="bill-container">

    <div class="bill">

        <!-- Clinic Header -->

        <div class="clinic-header">

            <h2>
                Sunrise Dental Clinic
            </h2>

            <p class="mb-1">
                Dental Care & Treatment Center
            </p>

            <small>
                Colombo, Sri Lanka
            </small>

        </div>


        <div class="d-flex justify-content-between mb-4">

            <div>

                <strong>Bill No:</strong>

                <span id="billNo">
                    B001
                </span>

            </div>

            <div>

                <strong>Date:</strong>

                <span id="billDate"></span>

            </div>

        </div>


        <!-- Patient Information -->

        <h5 class="mb-3">
            Patient Information
        </h5>

        <div class="row g-3 mb-4">

            <div class="col-md-6">

                <label class="fw-bold">
                    Appointment No
                </label>

                <input type="text"
                       id="appointmentNo"
                       name="appointmentNo"
                       class="form-control"
                       placeholder="APT001">

            </div>


            <div class="col-md-6">

                <label class="fw-bold">
                    Patient Name
                </label>

                <input type="text"
                       id="patientName"
                       name="patientName"
                       class="form-control"
                       placeholder="Patient name">

            </div>

        </div>


        <!-- Billing -->

        <h5 class="mb-3">
            Treatment Details
        </h5>

        <div class="table-responsive">

            <table class="table table-bordered">

                <thead class="table-light">

                <tr>

                    <th>
                        Treatment
                    </th>

                    <th width="200">
                        Treatment Fee
                    </th>

                </tr>

                </thead>

                <tbody>

                <tr>

                    <td>

                        <select id="treatment"
                                class="form-select"
                                onchange="calculateTotal()">

                            <option value="">
                                Select Treatment
                            </option>

                            <option value="1000">
                                Consultation
                            </option>

                            <option value="2500">
                                Dental Cleaning
                            </option>

                            <option value="3500">
                                Dental Filling
                            </option>

                            <option value="5000">
                                Tooth Extraction
                            </option>

                            <option value="12000">
                                Root Canal
                            </option>

                        </select>

                    </td>

                    <td>

                        <input type="number"
                               id="treatmentFee"
                               class="form-control"
                               value="0"
                               readonly>

                    </td>

                </tr>

                <tr>

                    <td>
                        Consultation Fee
                    </td>

                    <td>

                        <input type="number"
                               id="consultationFee"
                               class="form-control"
                               value="500"
                               min="0"
                               oninput="calculateTotal()">

                    </td>

                </tr>


                <tr class="total-row">

                    <td class="text-end">
                        Total
                    </td>

                    <td>

                        Rs.
                        <span id="total">
                            0.00
                        </span>

                    </td>

                </tr>

                </tbody>

            </table>

        </div>


        <!-- Buttons -->

        <div class="text-end mt-4 no-print">

            <button type="button"
                    onclick="calculateTotal()"
                    class="btn btn-primary">

                <i class="bi bi-calculator"></i>
                Calculate

            </button>


            <button type="button"
                    onclick="window.print()"
                    class="btn btn-success">

                <i class="bi bi-printer"></i>
                Print Bill

            </button>


            <a href="dashboard.jsp"
               class="btn btn-secondary">

                Back

            </a>

        </div>

    </div>

</div>


<script>

    // Set current date

    document.getElementById("billDate").innerText =
        new Date().toLocaleDateString();


    function calculateTotal() {

        const treatment =
            document.getElementById("treatment");

        const treatmentFee =
            parseFloat(treatment.value) || 0;

        const consultationFee =
            parseFloat(
                document.getElementById("consultationFee").value
            ) || 0;


        document.getElementById("treatmentFee").value =
            treatmentFee;


        const total =
            treatmentFee + consultationFee;


        document.getElementById("total").innerText =
            total.toFixed(2);

    }

</script>

</body>
</html>