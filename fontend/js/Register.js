document.addEventListener("DOMContentLoaded", function() {
    // Initial fetch when the page loads
    fetchCourseData();
});

function fetchCourseData() {
    const semesterId = document.getElementById('semesterSelect').value;
    fetch(`http://localhost:8080/api/dkhp/CourseOpening/${semesterId}`)
        .then(response => response.json())
        .then(data => {
            updateCoursesTable(data.data.dsKhoaHoc);
        })
        .catch(error => console.error('Error fetching course data:', error));
}

function updateCoursesTable(courses) {
    const tbody = document.querySelector('.table-register tbody');
    tbody.innerHTML = ''; // Clear existing rows
    courses.forEach((course, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
        <input type="radio" name="courseSelection" onclick="fetchCourseDetails(${1})">
            <td>${index + 1}</td>
            <td>${course.id}</td> <!-- Assuming Mã MH cũ could be the same as course id for simplicity -->
            <td>${course.id}</td>
            <td>${course.tenMonHoc}</td>
            <td>${course.soTinChi}</td>
            <td></td> <!-- Placeholder for Bắt buộc -->
            <td>${course.monTienQuyet.join(', ') || 'None'}</td> <!-- Process prerequisites -->
            <td></td> <!-- Placeholder for Học phần tương đương -->
        `;
        tbody.appendChild(row);
    });
}

document.addEventListener("DOMContentLoaded", function() {
    fetchEnrollmentData();
});

function fetchEnrollmentData() {
    fetch('http://localhost:8080/api/dkhp/Enrollment/1')
        .then(response => response.json())
        .then(data => updateEnrollmentTable(data.data))
        .catch(error => console.error('Error fetching enrollment data:', error));
}

function updateEnrollmentTable(enrollments) {
    const tbody = document.querySelector('.table-register2 tbody');
    tbody.innerHTML = ''; // Clear existing rows
    enrollments.forEach((enrollment, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td> <img src="../img/option.png" alt=""></td>
            <td>${index + 1}</td>
            <td>${enrollment.classId}</td>
            <td>${enrollment.tenMonHoc}</td>
            <td>${enrollment.tenLopHocPhan}</td>
            <td>${enrollment.soTinChi}</td>
            <td> - </td> <!-- Placeholder for Nhóm TH -->
            <td>${enrollment.hocPhi}</td>
            <td>${enrollment.ngayKetThuc}</td> <!-- Assuming this is the payment deadline -->
            <td><img src="../img/check.png" alt=""></td> <!-- Placeholder for Thu -->
            <td>${enrollment.trangThaiLop}</td>
            <td>${enrollment.ngayBatDau} - ${enrollment.ngayKetThuc}</td>
            <td>${enrollment.trangThaiLop}</td>
        `;
        tbody.appendChild(row);
    });
}

function unregister(classId) {
    console.log('Unregistering class:', classId);
    // Add API call to unregister the class here
}