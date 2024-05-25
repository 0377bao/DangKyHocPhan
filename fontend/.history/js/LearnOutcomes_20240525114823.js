document.addEventListener("DOMContentLoaded", function() {
    function fetchResults() {
        fetch('http://localhost:8080/api/dkhp/ResultCourse/getResultDetail/1')
        .then(response => response.json())
        .then(data => {
            updateResultsTable(data.data);
        })
        .catch(error => console.error('Error fetching data:', error));
    }

    function updateResultsTable(data) {
        const tbody = document.querySelector('.table-subject tbody');
        tbody.innerHTML = ''; // Clear existing content

        data.forEach((semester) => {
            // Add semester header
            const semesterRow = document.createElement('tr');
            semesterRow.innerHTML = `<td colspan="12" style="padding: 5px 0; text-aligns: left;">${semester.hocKi}</td>`;
            tbody.appendChild(semesterRow);

            semester.ketQua.forEach((result, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${result.maLopHocPhan}</td>
                    <td>${result.tenMonHoc}</td>
                    <td>${result.soTinChi} </td> <!-- Placeholder for number of certificates -->
                    <td>${formatScore(result.giuaKi)}</td>
                    <td>
                        <table class="nested_table" style="border-collapse: separate; height: 20px;">
                            <tr>
                                ${result.diemThuongXuyen.length != 0 ? result.diemThuongXuyen.map(score =>`<td>${score}</td>`) : `<td> </td>`}
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table class="nested_table">
                        <tr>
                        ${result.diemThucHanh.length != 0 ? result.diemThucHanh.map(score => `<td>${score}</td>`) : `<td> </td>`}
                    </tr>
                        </table>
                    </td>
                    <td>${formatScore(result.diemCuoiKi)}</td>
                    <td>${formatScore(result.diemTongKet)}</td>
                    <td>${formatScore(result.thangDiem4)}</td>
                    <td>${result.thangDiem4== 0.0 ? "Không đạt" : "Đạt"}</td>
                `;
                tbody.appendChild(row);
            });
        });
    }

    function averageScore(scores) {
        if (scores.length === 0) return null; // Return null if no scores
        let sum = scores.reduce((acc, score) => acc + score, 0);
        return sum / scores.length;
    }

    function formatScore(score) {
        return score === null ? 'ㅤ' : score.toFixed(2);
    }

    fetchResults();
});


//Hiển thị username
document.addEventListener("DOMContentLoaded", function() {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('User not logged in');
        window.location.href = "LoginHomePage.html";
        return;
    }


    function fetchStudentDetails() {
        fetch(`http://localhost:8080/api/dkhp/Student/getStudentDetail/${userId}`)
            .then(response => response.json())
            .then(data => {
                updateStudentDetails(data);
            })
            .catch(error => console.error('Error fetching student details:', error));
    }

    function updateStudentDetails(student) {
        document.getElementById('studentName').textContent = student.hoTen;
    }

    fetchStudentDetails();
});