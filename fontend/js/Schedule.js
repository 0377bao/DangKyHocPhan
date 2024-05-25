function getTimeBlock(periods) {
    if ((periods >= 1 && periods <= 6)) {
        return "Sáng"; // Morning: periods 1-6
    } else if (periods >= 7 && periods <= 12) {
        return "Chiều"; // Noon: periods 7-9
    } else {
        return "Tối"; // Afternoon: periods 10 onwards
    }
}

document.addEventListener("DOMContentLoaded", function() {
    function fetchSchedule() {
        let currentStartDate = new Date();
        let currentEndDate = new Date();
        currentEndDate.setDate(currentEndDate.getDate() + 7);

        const startDate = formatDate(currentStartDate);
        const endDate = formatDate(currentEndDate);

        console.log(`http://localhost:8080/api/dkhp/Schedule/getScheduleOfStudent?id=1&tuNgay=${startDate}&denNgay=${endDate}`);
        
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

    function formatDate(date) {
        return date.toISOString().split('T')[0];
    }

    function updateScheduleTable(scheduleData) {
        const tbody = document.querySelector('.table-subject tbody');
        tbody.innerHTML = ''; // Clear existing table rows

        const daysOfWeek = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];
        const timeBlocks = { 'Sáng': [], 'Chiều': [], 'Tối': [] };

        scheduleData.forEach(item => {
            const block = getTimeBlock(parseInt(item.tietHoc.split('-')[0]));
            if (block) {
                timeBlocks[block].push(item);
            }
        });

        Object.entries(timeBlocks).forEach(([block, items]) => {
            const row = document.createElement('tr');
            row.innerHTML = `<td class="block-sesstion">${block}</td>`;
            daysOfWeek.forEach(day => {
                row.innerHTML += `<td>${items.filter(item => item.thu === day).map(item => formatScheduleData(item)).join('<br>')}</td>`;
            });
            tbody.appendChild(row);
        });
    }

    function formatScheduleData(scheduleItem) {
        let classBlock = "";
        if(scheduleItem.loaiLich == 'Lý thuyết') classBlock = 'block block--theory';
        else if(scheduleItem.loaiLich == 'Thực hành') classBlock = 'block block--practice';
        else if(scheduleItem.loaiLich == 'Trực tuyến') classBlock = 'block block--online';
        return  `
            <span class="${classBlock}">
            <span class="course-name">${scheduleItem.tenMonHoc}</span><br>
            ${scheduleItem.tenLop} - ${scheduleItem.maLopHocPhan}<br>
            Tiết: ${scheduleItem.tietHoc}<br>
            Phòng: ${scheduleItem.phongHoc}<br>
            GV: ${scheduleItem.tenGiangVien}<br>
            ${scheduleItem.loaiLich == 'Trực tuyến' ? "Ghi chú: " + scheduleItem.ghiChu : ""}</span>`;
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