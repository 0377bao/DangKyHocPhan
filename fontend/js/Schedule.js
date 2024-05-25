function getTimeBlock(periods) {
    if ((periods >= 1 && periods <= 3) || (periods >= 4 && periods <= 6)) {
        return "Sáng"; // Morning: periods 1-6
    } else if (periods >= 7 && periods <= 9) {
        return "Trưa"; // Noon: periods 7-9
    } else {
        return "Chiều"; // Afternoon: periods 10 onwards
    }
}

document.addEventListener("DOMContentLoaded", function() {
    function fetchSchedule() {
        const startDate = '2021-03-01';
        const endDate = '2022-05-07';
        fetch(`http://localhost:8080/api/dkhp/Schedule/getScheduleOfStudent?id=1&tuNgay=${startDate}&denNgay=${endDate}`)
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success' && Array.isArray(data.data)) {
                    updateScheduleTable(data.data);
                } else {
                    console.error('Expected array in data.data, received:', data);
                }
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    function updateScheduleTable(scheduleData) {
        const tbody = document.querySelector('.table-subject tbody');
        tbody.innerHTML = ''; // Clear existing table rows

        const daysOfWeek = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];
        const timeBlocks = { 'Sáng': [], 'Trưa': [], 'Chiều': [] };

        scheduleData.forEach(item => {
            const block = getTimeBlock(parseInt(item.tietHoc.split('-')[0]));
            if (block) {
                timeBlocks[block].push(item);
            }
        });

        Object.entries(timeBlocks).forEach(([block, items]) => {
            const row = document.createElement('tr');
            row.innerHTML = `<td>${block}</td>`;
            daysOfWeek.forEach(day => {
                row.innerHTML += `<td>${items.filter(item => item.thu === day).map(item => formatScheduleData(item)).join('<br>')}</td>`;
            });
            tbody.appendChild(row);
        });
    }

    function formatScheduleData(scheduleItem) {
        return `${scheduleItem.tenMonHoc} <br>
        (${scheduleItem.loaiLich})<br>
        GV:${scheduleItem.tenGiangVien}<br>Phòng: 
        ${scheduleItem.phongHoc}
        Tiết:${scheduleItem.tietHoc} 
        `;
       
    }

    fetchSchedule();
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