const baseUrl = "http://localhost:8089/hospitals";
let hospitalData = [];
async function loadHospitals() {
    let res = await fetch(baseUrl);
    hospitalData = await res.json();
    displayHospitals(hospitalData);
}
function displayHospitals(hospitals) {
    const tbody = document.getElementById("hospitalTableBody");
    tbody.innerHTML = "";
    hospitals.forEach(h => {
        tbody.innerHTML += `
            <tr>
                <td>${h.id}</td>
                <td>${h.name}</td>
                <td>${h.address}</td>
                <td>
                    <button class="btn btn-sm btn-danger" onclick="deleteHospital(${h.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}
async function deleteHospital(id) {
    await fetch(`${baseUrl}/deleteHospital/${id}`, { method: "DELETE" });
    loadHospitals();
}

async function searchHospital() {
    const type = document.getElementById("searchType").value;
    const query = document.getElementById("searchInput").value.trim();
    if (!query) {
        loadHospitals();
        return;
    }
    let res;
    if (type === "id") {
        res = await fetch(`${baseUrl}/${query}`);
        if (res.ok) {
            const hospital = await res.json();
            displayHospitals([hospital]);
        } else {
            displayHospitals([]);
        }
    } else if (type === "name") {
        res = await fetch(`${baseUrl}/searchByHospitalName/${query}`);
        displayHospitals(await res.json());
    } else if (type === "address") {
        res = await fetch(`${baseUrl}/searchByHospitalAddress/${query}`);
        displayHospitals(await res.json());
    }
}



