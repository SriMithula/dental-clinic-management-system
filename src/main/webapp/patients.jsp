<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Patients - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <style>
        body {
            background: #f5f7fb;
            font-family: Arial, sans-serif;
        }

        .patients-container {
            max-width: 1100px;
            margin: 40px auto;
            padding: 30px;
        }

        .patients-card {
            background: white;
            border-radius: 12px;
            padding: 25px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }

        .patients-title {
            color: #1677f2;
            margin-bottom: 25px;
        }

        table {
            width: 100%;
        }

        th {
            background: #1677f2 !important;
            color: white !important;
        }

        td, th {
            padding: 12px;
            vertical-align: middle;
        }
    </style>
</head>

<body>

<div class="patients-container">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="patients-title mb-0">Patient List</h2>

        <a href="${pageContext.request.contextPath}/dashboard"
           class="btn btn-outline-primary">
            Back to Dashboard
        </a>
    </div>

    <div class="patients-card">

        <table class="table table-bordered table-hover">

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Contact Number</th>
                    <th>Address</th>
                </tr>
            </thead>

            <tbody id="patientsTableBody">
            </tbody>

        </table>

    </div>

</div>


<script>
    fetch('${pageContext.request.contextPath}/PatientSearchController?search=')
        .then(response => response.json())
        .then(patients => {

            const tableBody =
                document.getElementById("patientsTableBody");

            patients.forEach(patient => {

                const row = document.createElement("tr");

                row.innerHTML =
                    '<td>' + patient.id + '</td>' +
                    '<td>' + patient.name + '</td>' +
                    '<td>' + patient.tel + '</td>' +
                    '<td>' + patient.address + '</td>';

                tableBody.appendChild(row);
            });

        })
        .catch(error => {
            console.error("Error loading patients:", error);
        });
</script>

</body>
</html>