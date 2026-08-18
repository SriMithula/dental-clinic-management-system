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

    <title>Help - Sunrise Dental Clinic</title>

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

        .help-container {
            max-width: 1000px;
            margin: 40px auto;
        }

        .header {
            background: #0d6efd;
            color: white;
            padding: 25px;
            border-radius: 12px 12px 0 0;
        }

        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.07);
            margin-bottom: 20px;
        }

        .step {
            display: flex;
            gap: 15px;
            margin-bottom: 20px;
        }

        .step-number {
            min-width: 40px;
            height: 40px;
            background: #0d6efd;
            color: white;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: bold;
        }

        .step-content h6 {
            margin-bottom: 5px;
            font-weight: bold;
        }

        .icon-box {
            width: 45px;
            height: 45px;
            background: #e7f0ff;
            color: #0d6efd;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 22px;
        }

    </style>

</head>

<body>

<div class="help-container">

    <!-- Header -->

    <div class="header">

        <h3>
            <i class="bi bi-question-circle"></i>
            Help & User Guide
        </h3>

        <p class="mb-0">
            Sunrise Dental Clinic Management System
        </p>

    </div>


    <!-- Getting Started -->

    <div class="card p-4">

        <h5 class="mb-4">
            <i class="bi bi-info-circle text-primary"></i>
            Getting Started
        </h5>

        <p>
            This system is designed to help clinic staff manage
            patient appointments and billing efficiently.
        </p>

        <p class="mb-0">
            Use the navigation menu on the dashboard to access
            appointments, patient details, billing and other
            system functions.
        </p>

    </div>


    <!-- Login -->

    <div class="card p-4">

        <h5 class="mb-4">
            <i class="bi bi-box-arrow-in-right text-primary"></i>
            1. Login
        </h5>

        <div class="step">

            <div class="step-number">
                1
            </div>

            <div class="step-content">

                <h6>Open the Login Page</h6>

                <p class="text-muted mb-0">
                    Enter your authorized username and password
                    to access the system.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                2
            </div>

            <div class="step-content">

                <h6>Click Login</h6>

                <p class="text-muted mb-0">
                    If the credentials are valid, you will be
                    redirected to the dashboard.
                </p>

            </div>

        </div>

    </div>


    <!-- Appointment -->

    <div class="card p-4">

        <h5 class="mb-4">
            <i class="bi bi-calendar-plus text-primary"></i>
            2. Register an Appointment
        </h5>

        <div class="step">

            <div class="step-number">
                1
            </div>

            <div class="step-content">

                <h6>Open Appointments</h6>

                <p class="text-muted mb-0">
                    Select <strong>Appointments</strong> from the
                    dashboard menu.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                2
            </div>

            <div class="step-content">

                <h6>Enter Patient Information</h6>

                <p class="text-muted mb-0">
                    Enter the appointment number, patient name,
                    address and contact number.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                3
            </div>

            <div class="step-content">

                <h6>Select Dentist and Treatment</h6>

                <p class="text-muted mb-0">
                    Select the appropriate dentist and treatment
                    type from the available options.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                4
            </div>

            <div class="step-content">

                <h6>Select Date and Time</h6>

                <p class="text-muted mb-0">
                    Select the patient's appointment date and time.
                </p>

            </div>

        </div>


        <div class="step mb-0">

            <div class="step-number">
                5
            </div>

            <div class="step-content">

                <h6>Save Appointment</h6>

                <p class="text-muted mb-0">
                    Click <strong>Save Appointment</strong> to
                    store the appointment.
                </p>

            </div>

        </div>

    </div>


    <!-- Search Appointment -->

    <div class="card p-4">

        <h5 class="mb-4">
            <i class="bi bi-search text-primary"></i>
            3. Search Appointment
        </h5>

        <div class="step">

            <div class="step-number">
                1
            </div>

            <div class="step-content">

                <h6>Open Appointment Details</h6>

                <p class="text-muted mb-0">
                    Select <strong>Appointment Details</strong>
                    from the dashboard.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                2
            </div>

            <div class="step-content">

                <h6>Enter Appointment Number</h6>

                <p class="text-muted mb-0">
                    Enter the unique appointment number.
                </p>

            </div>

        </div>


        <div class="step mb-0">

            <div class="step-number">
                3
            </div>

            <div class="step-content">

                <h6>View Details</h6>

                <p class="text-muted mb-0">
                    Click Search to display the patient's
                    appointment information.
                </p>

            </div>

        </div>

    </div>


    <!-- Billing -->

    <div class="card p-4">

        <h5 class="mb-4">
            <i class="bi bi-receipt text-primary"></i>
            4. Create and Print Bill
        </h5>

        <div class="step">

            <div class="step-number">
                1
            </div>

            <div class="step-content">

                <h6>Open Billing</h6>

                <p class="text-muted mb-0">
                    Select <strong>Billing</strong> from the
                    dashboard.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                2
            </div>

            <div class="step-content">

                <h6>Enter Patient Details</h6>

                <p class="text-muted mb-0">
                    Enter or select the appointment and patient
                    information.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                3
            </div>

            <div class="step-content">

                <h6>Select Treatment</h6>

                <p class="text-muted mb-0">
                    Select the treatment performed for the patient.
                </p>

            </div>

        </div>


        <div class="step">

            <div class="step-number">
                4
            </div>

            <div class="step-content">

                <h6>Calculate Total</h6>

                <p class="text-muted mb-0">
                    Click the Calculate button to calculate the
                    treatment and consultation fees.
                </p>

            </div>

        </div>


        <div class="step mb-0">

            <div class="step-number">
                5
            </div>

            <div class="step-content">

                <h6>Print Bill</h6>

                <p class="text-muted mb-0">
                    Click <strong>Print Bill</strong> to print
                    the patient's receipt.
                </p>

            </div>

        </div>

    </div>


    <!-- Logout -->

    <div class="card p-4">

        <h5 class="mb-4">
            <i class="bi bi-box-arrow-right text-primary"></i>
            5. Logout
        </h5>

        <p class="mb-0">
            After completing your work, click
            <strong>Logout</strong> from the navigation menu
            to safely exit the system.
        </p>

    </div>


    <!-- Important Notes -->

    <div class="card p-4">

        <h5 class="mb-3">
            <i class="bi bi-exclamation-triangle text-warning"></i>
            Important Notes
        </h5>

        <ul class="mb-0">

            <li>
                Always verify patient information before saving.
            </li>

            <li>
                Make sure the appointment date and time are correct.
            </li>

            <li>
                Check the treatment type before generating a bill.
            </li>

            <li>
                Only authorized staff should access the system.
            </li>

            <li>
                Always logout after completing your work.
            </li>

        </ul>

    </div>


    <!-- Back Button -->

    <div class="text-center">

        <a href="dashboard.jsp"
           class="btn btn-primary">

            <i class="bi bi-arrow-left"></i>
            Back to Dashboard

        </a>

    </div>

</div>

</body>
</html>