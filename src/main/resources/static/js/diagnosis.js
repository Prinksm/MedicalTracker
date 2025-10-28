const diagnosisBaseUrl = "http://localhost:8089/diagnosis";

async function loadDiagnosis() {
    try {
        let res = await fetch(diagnosisBaseUrl);
        if (!res.ok) throw new Error("Failed to fetch diagnosis");
        let diagnosis = await res.json();
        displayDiagnosis(diagnosis);
    } catch (err) {
        console.error(err);
    }
}

function displayDiagnosis(diagnosis) {
    const tbody = document.getElementById("diagnosisTableBody");
    tbody.innerHTML = "";
    diagnosis.forEach(d => {
        tbody.innerHTML += `
            <tr>
                <td>${d.id}</td>
                <td>${d.patientName}</td>
                <td>${d.description}</td>
                <td>
                    <button class="btn btn-sm btn-danger" onclick="deleteDiagnosis(${d.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

async function deleteDiagnosis(id) {
    await fetch(`${diagnosisBaseUrl}/deleteDiagnosis/${id}`, { method: "DELETE" });
        loadDiagnosis();
}


async function searchDiagnosis() {
    const type = document.getElementById("searchType").value;
    const query = document.getElementById("searchInput").value.trim();
    if (!query) {
        loadDiagnosis();
        return;
    }
    let res;
    if (type === "id") {
        res = await fetch(`${diagnosisBaseUrl}/${query}`);
        if (res.ok) {
            const diagnosis = await res.json();
            displayDiagnosis([diagnosis]);
        } else {
            displayDiagnosis([]);
        }
    } else if (type === "patientName") {
        res = await fetch(`${diagnosisBaseUrl}/searchByPatientName/${query}`);
        displayDiagnosis(await res.json());
    }
}


loadDiagnosis()
