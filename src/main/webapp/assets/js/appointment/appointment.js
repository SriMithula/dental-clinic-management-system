function searchPatient() {
    const search = document.getElementById("patientSearch").value.trim();
    if (search === "") {
        alert("Please enter patient name or contact number.");
        return;
    }


    const results = document.getElementById("patientResults");
    results.innerHTML =
        `<div class="list-group-item text-center">
            <div class="spinner-border spinner-border-sm text-primary">
            </div>
            Searching...
         </div>`;


    fetch(`http://localhost:8080/SunriseDental/PatientSearchController?search=${search}`)
    .then(response => {
        if (!response.ok) {
            throw new Error("Search failed");
        }
        return response.json();
    })
    .then(patients => {
        results.innerHTML = "";
        if (!patients || patients.length === 0) {
            results.innerHTML = `
                <div class="list-group-item text-center text-muted">

                    <i class="bi bi-person-x"></i>

                    No patient found.

                    <br>

                    <button type="button"
                            class="btn btn-sm btn-success mt-2"
                            onclick="newPatient()">

                        Register New Patient

                    </button>

                </div>
            `;

            return;
        }

        patients.forEach(patient => {
            const item =
                document.createElement("button");


            item.type = "button";

            item.className =
                "list-group-item list-group-item-action patient-item";


            item.innerHTML = `

                <div class="d-flex justify-content-between">

                    <div>

                        <strong>
                            ${escapeHtml(patient.name)}
                        </strong>

                        <br>

                        <small class="text-muted">

                            ${escapeHtml(patient.tel)}

                        </small>

                    </div>

                    <div>

                        <i class="bi bi-chevron-right"></i>

                    </div>

                </div>

            `;


            item.onclick = function() {

                selectPatient(patient);

            };


            results.appendChild(item);

        });

    })

    .catch(error => {

        console.error(error);

        results.innerHTML = `

            <div class="list-group-item text-danger">

                <i class="bi bi-exclamation-triangle"></i>

                Unable to search patients.

                Please try again.

            </div>

        `;

    });

}

function selectPatient(patient) {

    document.getElementById("patientId").value =
        patient.id || "";


    document.getElementById("patientName").value =
        patient.name || "";


    document.getElementById("contactNo").value =
        patient.tel || "";


    document.getElementById("address").value =
        patient.address || "";


    document.getElementById("selectedPatientName").innerHTML = `

        <strong>
            ${escapeHtml(patient.name)}
        </strong>

        <br>

        <small>
            ${escapeHtml(patient.contactNo)}
        </small>

    `;


    document.getElementById("selectedPatient").style.display =
        "block";


    document.getElementById("patientResults").innerHTML =
        "";


    document.getElementById("patientSearch").value =
        patient.name || "";


    // Existing patient
    document.getElementById("patientName").readOnly = true;

    document.getElementById("contactNo").readOnly = true;

    document.getElementById("address").readOnly = true;

}


/* =====================================================
   CLEAR PATIENT
   ===================================================== */

function clearPatient() {

    document.getElementById("patientId").value = "";

    document.getElementById("patientName").value = "";

    document.getElementById("contactNo").value = "";

    document.getElementById("address").value = "";

    document.getElementById("patientSearch").value = "";

    document.getElementById("patientResults").innerHTML = "";

    document.getElementById("selectedPatient").style.display =
        "none";


    document.getElementById("patientName").readOnly = false;

    document.getElementById("contactNo").readOnly = false;

    document.getElementById("address").readOnly = false;

}

function newPatient() {

    clearPatient();

    document.getElementById("patientName").focus();

}

function escapeHtml(value) {

    if (value === null || value === undefined) {
        return "";
    }

    return String(value)

        .replace(/&/g, "&amp;")

        .replace(/</g, "&lt;")

        .replace(/>/g, "&gt;")

        .replace(/"/g, "&quot;")

        .replace(/'/g, "&#039;");

}





const today =
    new Date().toISOString().split("T")[0];


document
    .getElementById("appointmentDate")
    .setAttribute("min", today);
	
document.getElementById("appointmentForm").addEventListener("submit", function (event) {

    const patientId =
        document.getElementById("patientId").value.trim();

    const patientName =
        document.getElementById("patientName").value.trim();

    const contact =
        document.getElementById("contactNo").value.trim();

    const address =
        document.getElementById("address").value.trim();

    const appointmentNo =
        document.getElementById("appointmentNo").value.trim();

    const dentist =
        document.getElementById("dentist").value;

    const treatment =
        document.getElementById("treatmentType").value;

    const appointmentDate =
        document.getElementById("appointmentDate").value;

    const appointmentTime =
        document.getElementById("appointmentTime").value;


    // New patient
    if (patientId === "") {
        document.getElementById("patientId").value = "-1";
    }



    if (patientName === "") {

        event.preventDefault();

        alert("Please enter patient name.");

        document.getElementById("patientName").focus();

    } else if (contact === "") {

        event.preventDefault();

        alert("Please enter contact number.");

        document.getElementById("contactNo").focus();

    } else if (!/^[0-9]{10}$/.test(contact)) {

        event.preventDefault();

        alert("Please enter a valid 10-digit contact number.");

        document.getElementById("contactNo").focus();

    } else if (address === "") {

        event.preventDefault();

        alert("Please enter patient address.");

        document.getElementById("address").focus();

    } else if (appointmentNo === "") {

        event.preventDefault();

        alert("Please enter appointment number.");

        document.getElementById("appointmentNo").focus();

    } else if (dentist === "") {

        event.preventDefault();

        alert("Please select a dentist.");

        document.getElementById("dentist").focus();

    } else if (treatment === "") {

        event.preventDefault();

        alert("Please select a treatment.");

        document.getElementById("treatmentType").focus();

    } else if (appointmentDate === "") {

        event.preventDefault();

        alert("Please select appointment date.");

        document.getElementById("appointmentDate").focus();

    } else if (
        appointmentDate <
        new Date().toISOString().split("T")[0]
    ) {

        event.preventDefault();

        alert("Appointment date cannot be in the past.");

        document.getElementById("appointmentDate").focus();

    } else if (appointmentTime === "") {

        event.preventDefault();

        alert("Please select appointment time.");

        document.getElementById("appointmentTime").focus();

    } else {

        console.log("Form validation successful.");

    }

});

