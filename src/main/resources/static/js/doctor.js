const doctorBaseUrl = "http://localhost:8089/doctors";

async function loadDoctors() {
    try {
        let res = await fetch(doctorBaseUrl);
        if (!res.ok) throw new Error("Failed to fetch doctors");
        let doctors = await res.json();
        displayDoctors(doctors);
    } catch (err) {
        console.error(err);
    }
}

function displayDoctors(doctors) {
    const tbody = document.getElementById("doctorTableBody");
    tbody.innerHTML = "";
    doctors.forEach(d => {
        tbody.innerHTML += `
            <tr>
                <td>${d.id}</td>
                <td>${d.name}</td>
                <td>${d.specialization}</td>
                <td>${d.hospitalName}</td>
                <td>
                    <button class="btn btn-sm btn-danger" onclick="deleteDoctor(${d.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

async function deleteDoctor(id) {
    await fetch(`${doctorBaseUrl}/deleteDoctor/${id}`, { method: "DELETE" });
        loadDoctors();
}


async function searchDoctor() {
    const type = document.getElementById("searchType").value;
    const query = document.getElementById("searchInput").value.trim();
    if (!query) {
        loadDoctors();
        return;
    }
    let res;
    if (type === "id") {
        res = await fetch(`${doctorBaseUrl}/${query}`);
        if (res.ok) {
            const doctor = await res.json();
            displayDoctors([doctor]);
        } else {
            displayDoctors([]);
        }
    } else if (type === "name") {
        res = await fetch(`${doctorBaseUrl}/searchByDoctorName/${query}`);
        displayDoctors(await res.json());
    } else if (type === "specialisation") {
        res = await fetch(`${doctorBaseUrl}/searchByDoctorSpecialisation/${query}`);
        displayDoctors(await res.json());
    }
}



