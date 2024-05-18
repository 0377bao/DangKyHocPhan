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
            semesterRow.innerHTML = `<td colspan="9">${semester.hocKi}</td>`;
            tbody.appendChild(semesterRow);

            semester.ketQua.forEach((result, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${result.maLopHocPhan}</td>
                    <td>${result.tenMonHoc}</td>
                    <td>ㅤ</td> <!-- Placeholder for number of certificates -->
                    <td>${formatScore(averageScore(result.diemThuongXuyen))}</td>
                    <td>${formatScore(averageScore(result.diemThuongXuyen))}</td>
                    <td>${formatScore(averageScore(result.diemThucHanh))}</td>
                    <td>${formatScore(result.diemCuoiKi)}</td>
                    <td>${formatScore(result.diemTongKet)}</td>
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