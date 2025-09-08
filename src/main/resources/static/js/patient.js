const PatientUrl = "http://localhost:8089/patients";
async function loadPatients() {
    try {
        let response = await fetch(PatientUrl);
        let patients = await response.json();
        const tableBody = document.getElementById("patientTableBody");
        console.log("patients",patients)
        tableBody.innerHTML = "";
        patients.forEach(patient => {
            let row = `<tr>
                <td>${patient.id}</td>
                <td>${patient.name}</td>
                <td>${patient.age}</td>
                <td>${patient.gender}</td>
                <td>${patient.doctorName}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deletePatient(${patient.id})">Delete</button>
                </td>
            </tr>`;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error("Error loading patients:", error);
    }
}
async function deletePatient(id) {
    if (confirm("Are you sure you want to delete this patient?")) {
        await fetch(`${PatientUrl}/deletePatient/${id}`, { method: "DELETE" });
        loadPatients();
    }
}
loadPatients();

async function loadSearchPatients(patients) {
    try {
        const tableBody = document.getElementById("patientTableBody");
        console.log("patients",patients)
        tableBody.innerHTML = "";
        patients.forEach(patient => {
            let row = `<tr>
                <td>${patient.id}</td>
                <td>${patient.name}</td>
                <td>${patient.age}</td>
                <td>${patient.gender}</td>
                <td>${patient.doctorName}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deletePatient(${patient.id})">Delete</button>
                </td>
            </tr>`;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error("Error loading patients:", error);
    }
}
async function deletePatient(id) {
    if (confirm("Are you sure you want to delete this patient?")) {
        await fetch(`${PatientUrl}/deletePatient/${id}`, { method: "DELETE" });
        loadPatients();
    }
}


async function searchPatient() {
    const type = document.getElementById("searchType").value;
    const query = document.getElementById("searchInput").value.trim();
    if (!query) {
        loadSearchPatients();
        return;
    }
    let res;
    if (type === "id") {
        res = await fetch(`${PatientUrl}/${query}`);
        if (res.ok) {
            const patient = await res.json();
            loadSearchPatients([patient]);
        } else {
            loadSearchPatients([]);
        }
    } else if (type === "name") {
        res = await fetch(`${PatientUrl}/searchByPatientName/${query}`);
        loadSearchPatients(await res.json());
    } else if (type === "doctorName") {
        res = await fetch(`${PatientUrl}/searchByDoctorName/${query}`);
        loadSearchPatients(await res.json());
    }
}




