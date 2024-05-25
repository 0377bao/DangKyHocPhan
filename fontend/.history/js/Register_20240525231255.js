
        let selectedClassId = null;
    
        function login() {
            // Chuyển hướng sang trang Home.html
            window.location.href = "LoginRegister.html";
        }
    
        document.addEventListener("DOMContentLoaded", function() {
            // Initial fetch when the page loads
            fetchCourseData();
            fetchEnrollmentData();
        });
    
        function fetchCourseData() {
            const semesterId = document.getElementById('semesterSelect').value;
            fetch(`http://localhost:8080/api/dkhp/CourseOpening/${semesterId}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Fetched course data:', data); // Debugging line
                    updateCoursesTable(data.data.dsKhoaHoc);
                })
                .catch(error => console.error('Error fetching course data:', error));
        }
    
        function updateCoursesTable(courses) {
            const tbody = document.querySelector('.table-register tbody');
            tbody.innerHTML = ''; // Clear existing rows
            courses.forEach((course, index) => {
                const prerequisites = course.monTienQuyet?.map((item) => item.tenMonHoc).join(', ') || '';
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td><input type="radio" name="courseSelection" onclick="fetchClassDetails(${course.id})"></td>
                    <td>${index + 1}</td>
                    <td>${course.id}</td>
                    <td>${course.id}</td>
                    <td>${course.tenMonHoc}</td>
                    <td>${course.soTinChi}</td>
                    <td><img src="../img/check.png" alt=""></td>
                    <td>${prerequisites}</td>
                    <td></td>
                `;
                tbody.appendChild(row);
            });
        }
    
        function fetchClassDetails(courseId) {
            fetch(`http://localhost:8080/api/admin/clazz/clazzPlan?courseId=${courseId}&status=Chờ sinh viên đăng ký`)
                .then(response => response.json())
                .then(data => {
                    console.log('Fetched class details:', data); // Debugging line
                    updateClassTable(data.data);
                    document.querySelector('.content-subject4').style.display = 'block';
                })
                .catch(error => console.error('Error fetching class details:', error));
        }
    
        function updateClassTable(classes) {
            const tbody = document.querySelector('.table-register4 tbody');
            tbody.innerHTML = ''; // Clear previous entries
            classes.forEach((clazz, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td><input type="radio" name="classSelection" onclick="fetchCourseDetails(${clazz.maLop}); selectedClassId = ${clazz.maLop};"></td>
                    <td>${index + 1}</td>
                    <td>${clazz.maLop}</td>
                    <td>${clazz.tenMonHoc}</td>
                    <td>${clazz.tenLop}</td>
                    <td>${clazz.siSoToiDa}</td>
                    <td>${clazz.siSoHienTai}</td>
                    <td>${clazz.trangThai}</td>
                `;
                tbody.appendChild(row);
            });
        }
    
        function fetchCourseDetails(classId) {
            fetch(`http://localhost:8080/api/dkhp/Enrollment/detail/${classId}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Fetched course details:', data); // Debugging line
                    updateCourseDetailsTable(data.data);
                    document.querySelector('.content-subject1').style.display = 'block';
                })
                .catch(error => console.error('Error fetching course details:', error));
        }
    
        function updateCourseDetailsTable(details) {
            const tbody = document.querySelector('#courseDetailTable tbody');
            tbody.innerHTML = ''; // Clear previous entries
            details.forEach((detail, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${detail.thu}</td>
                    <td>${detail.loaiLich}</td>
                    <td>${detail.phongHoc}</td>
                    <td>${detail.tietHoc}</td>
                    <td>Cơ sở 1</td>
                    <td>${detail.tenGiangVien}</td>
                    <td><button type="button" style="background-color: #0069d9; color: #fff; border-width: 0; height: 25px; width: 45%;">Xem</button></td>
                `;
                tbody.appendChild(row);
            });
        }
    
        function registerCourse() {
            const semesterId = document.getElementById('semesterSelect').value;
            const semesterText = document.getElementById('semesterSelect').options[document.getElementById('semesterSelect').selectedIndex].text;
            const requestBody = {
                "student_id": 1,
                "class_id": selectedClassId,
                "ngayBatDau": "2021-08-18",
                "ngayKetThuc": "2021-12-10",
                "hocKi": semesterText
            };
    
            fetch('http://localhost:8080/api/dkhp/Enrollment/createEnrollment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Registration successful:', data);
                alert('Đăng ký thành công!');
                addEnrollmentToTable(data.data); // Add new enrollment to the table
            })
            .catch(error => console.error('Error registering course:', error));
        }
    
        function addEnrollmentToTable(enrollment) {
            const tbody = document.querySelector('.table-register3 tbody');
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><img src="../img/option.png" alt="" onclick="showCancelOption(this, ${enrollment.classId})"></td>
                <td>${tbody.children.length + 1}</td>
                <td>${enrollment.classId}</td>
                <td>${enrollment.tenMonHoc}</td>
                <td>${enrollment.tenLopHocPhan}</td>
                <td>${enrollment.soTinChi}</td>
                <td>-</td>
                <td>${enrollment.hocPhi}</td>
                <td>${enrollment.ngayKetThuc}</td>
                <td><img src="../img/check.png" alt=""></td>
                <td>Đăng ký mới</td>
                <td>${enrollment.ngayBatDau}</td>
                <td>${enrollment.trangThaiLop}</td>
            `;
            tbody.appendChild(row);
        }
    
        function showCancelOption(icon, classId) {
            const cancelButton = document.createElement('button');
            cancelButton.innerText = 'Hủy';
            cancelButton.style.backgroundColor = '#d9534f';
            cancelButton.style.color = '#fff';
            cancelButton.style.borderWidth = '0';
            cancelButton.style.marginLeft = '10px';
            cancelButton.onclick = function() {
                cancelEnrollment(classId, icon.parentNode.parentNode);
            };
            icon.parentNode.appendChild(cancelButton);
            icon.style.display = 'none'; // Hide the option icon
        }
    
        function cancelEnrollment(classId, rowElement) {
            if (confirm('Bạn có chắc chắn muốn hủy lớp học phần này không?')) {
                const studentId = 1; // Assuming the student ID is 1, update as necessary
                fetch(`http://localhost:8080/api/dkhp/Enrollment/delete?studentId=${studentId}&classId=${classId}`, {
                    method: 'DELETE'
                })
                .then(response => response.json())
                .then(data => {
                    alert('Hủy đăng ký thành công!');
                    rowElement.remove(); // Remove the row from the table
                })
                .catch(error => console.error('Error cancelling enrollment:', error));
            }
        }
    
        function fetchEnrollmentData() {
            const semesterId = document.getElementById('semesterSelect').value;
            fetch(`http://localhost:8080/api/dkhp/Enrollment/${semesterId}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Fetched enrollment data:', data); // Debugging line
                    if (data.status === 'Failed') {
                        console.error(`Error: ${data.message}`);
                        return;
                    }
                    updateEnrollmentTables(data.data, semesterId);
                })
                .catch(error => console.error('Error fetching enrollment data:', error));
        }
    
        function updateEnrollmentTables(enrollments, semesterId) {
            const tbodyLocked = document.querySelector('.table-register2 tbody');
            const tbodyPending = document.querySelector('.table-register3 tbody');
            tbodyLocked.innerHTML = ''; // Clear existing rows
            tbodyPending.innerHTML = ''; // Clear existing rows
    
            console.log('Updating enrollment tables for semester:', semesterId, enrollments); // Debugging line
    
            if (!Array.isArray(enrollments)) {
                console.error('Enrollments data is not an array:', enrollments);
                return;
            }
            
            enrollments.forEach((enrollment, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td><img src="../img/option.png" alt="" onclick="showCancelOption(this, ${enrollment.classId})"></td>
                    <td>${index + 1}</td>
                    <td>${enrollment.classId}</td>
                    <td>${enrollment.tenMonHoc}</td>
                    <td>${enrollment.tenLopHocPhan}</td>
                    <td>${enrollment.soTinChi}</td>
                    <td>-</td>
                    <td>${enrollment.hocPhi}</td>
                    <td>${enrollment.ngayKetThuc}</td>
                    <td><img src="../img/check.png" alt=""></td>
                    <td>Đăng ký mới</td>
                    <td>${enrollment.ngayBatDau}</td>
                    <td>${enrollment.trangThaiLop}</td>
                `;
    
                if (enrollment.trangThaiLop === "Đã khóa lớp") {
                    tbodyLocked.appendChild(row);
                } else if (enrollment.trangThaiLop === "Chờ sinh viên đăng ký") {
                    tbodyPending.appendChild(row);
                }
            });
    
            // Show both tables
            const contentLocked = document.querySelector('.content-subject2:nth-of-type(1)');
            const contentPending = document.querySelector('.content-subject2:nth-of-type(2)');
    
            contentLocked.style.display = 'block';
            contentPending.style.display = 'block';
        }

function getDetailsInfoTeacher(classId) {
    fetch("http://localhost:8080/api/admin/teachers/getTeacherDetail/" + classId)
        .then(response => response.json())
        .then(data => {
            var infoTeacher = data.data;
            var html = `
                <h2 class="text-center">${infoTeacher.hoTen}</h2>
                <div class="info_teacher d-flex justify-content-between">
                    <div class="left_info">
                        <p>Mã nhân sự: <b>${infoTeacher.id}</b></p>
                        <p>Ngày sinh: <b>${(infoTeacher.ngaySinh).split('-').reverse().join('-')}</b></p>
                        <p>Chức vụ: <b>${infoTeacher.chuyenNganh}</b></p>
                    </div>
                    <div class="right_info">
                        <p>Giới tính: <b>${infoTeacher.gioiTinh ? "Nam": "Nữ"}</b></p>
                        <p>Nơi sinh: <b>${infoTeacher.diaChi}</b></p>
                        <p>Phòng ban: <b>${infoTeacher.khoa}</b></p>
                    </div>
                </div>
            `
        })
}
 