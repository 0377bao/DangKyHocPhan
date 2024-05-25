        const userId = localStorage.getItem('userId');
        if (!userId) {
            alert('User not logged in');
            window.location.href = "Home.html";
        }
        let selectedClassId = null;
        let ngayBatDau = '';
        let ngayKetThuc = '';
    
        function login() {
            // Chuyển hướng sang trang Home.html
            window.location.href = "LoginRegister.html";
        }
    
        document.addEventListener("DOMContentLoaded", function() {
            // Initial fetch when the page loads
            loadSelectItem();
        });

        function loadSelectItem() {
            fetch(`http://localhost:8080/api/dkhp/Student/getStudentDetail/${userId}`)
            .then(response => response.json())
            .then(data => {
                updateStudentDetails(data);
            })
            .catch(error => console.error('Error fetching student details:', error));
        }

        function updateStudentDetails(data) {
            const selectComponent = document.getElementById("semesterSelect");
            const content = [];
            for(let i = 0; i < 12; i++) {
                const yearCourse = Math.floor(i / 3);
                const year = (i + 1) % 3 == 0 ? 3 : (i + 1) % 3;
                const yearStudent = Number(data.nienKhoa.split('-')[0]);
                content.push(`<option value="Học Kỳ ${year} (${yearStudent + yearCourse}-${yearStudent + yearCourse + 1})">Học Kỳ ${year} (${yearStudent + yearCourse}-${yearStudent + yearCourse + 1})</option>`);
            }
            selectComponent.innerHTML = content.join('');
            fetchCourseData();
            fetchEnrollmentData();
        }
    
        function fetchCourseData() {
            const semesterId = document.getElementById('semesterSelect').value;
            fetch(`http://localhost:8080/api/dkhp/CourseOpening/getCourseOpening?studentId=${userId}&hocKy=${semesterId}`)
                .then(response => response.json())
                .then(data => {
                    updateCoursesTable(data.data?.dsKhoaHoc);
                })
                .catch(error => console.error('Error fetching course data:', error));
        }
    
        function updateCoursesTable(courses) {
            const tbody = document.querySelector('.table-register tbody');
            tbody.innerHTML = ''; // Clear existing rows
            courses?.forEach((course, index) => {
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
                    <td><input type="radio" name="classSelection" onclick="handleClickRadioButtonClass(${clazz.maLop},'${clazz.ngayBatDau}', '${clazz.ngayKetThuc}')"></td>
                    <td>${index + 1}</td>
                    <td>${clazz.maLop}</td>
                    <td>${clazz.tenMonHoc}</td>
                    <td>${clazz.tenLop}</td>
                    <td>${clazz.siSoToiDa}</td>
                    <td>${clazz.siSoHienTai}</td>
                    <td>${clazz.ngayBatDau}</td>
                    <td>${clazz.ngayKetThuc}</td>
                    <td>${clazz.trangThai}</td>
                `;
                tbody.appendChild(row);
            });
        }

        const handleClickRadioButtonClass = (classid, startDate, endDate) => {
            selectedClassId = classid;
            ngayBatDau = startDate;
            ngayKetThuc = endDate;
            fetchCourseDetails(selectedClassId);
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
                    <td>${detail.tietHoc}</td>
                    <td>${detail.loaiLich}</td>
                    <td>${detail.phongHoc}</td>
                    <td>Cơ sở 1</td>
                    <td>${detail.tenGiangVien}</td>
                    <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#teacherDetail" style="background-color: #0069d9; color: #fff; border-width: 0; padding: 5px 10px; border-radius: 5px;"
                    onclick="getDetailsInfoTeacher('${detail.maLopHocPhan}')"
                    >Xem</button></td>
                `;
                tbody.appendChild(row);
            });
        }
    
        function registerCourse() {
            const semester = document.getElementById('semesterSelect').value;
            const requestBody = {
                "student_id": userId,
                "class_id": selectedClassId,
                "ngayBatDau": ngayBatDau,
                "ngayKetThuc": ngayKetThuc,
                "hocKi": semester
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
                alert(data.message);
                if(data.status === 'Success') {
                    fetchCourseData();
                    fetchEnrollmentData();
                    document.querySelector(".content-subject4").style.display = 'none';
                    document.querySelector(".content-subject1").style.display = 'none';
                }
            })
            .catch(error => console.error('Error registering course:', error));
        }
    
        function addEnrollmentToTable(enrollment) {
            const tbody = document.querySelector('.table-register3 tbody');
            const row = document.createElement('tr');
            row.innerHTML = `
            <td><img src="../img/option.png" alt="" onclick="showCancelOption(this, ${enrollment.classId})"></td>
            <td>${index + 1}</td>
            <td>${enrollment.classId}</td>
            <td>${enrollment.tenMonHoc}</td>
            <td>${enrollment.tenLopHocPhan}</td>
            <td>${enrollment.soTinChi}</td>
            <td>${enrollment.hocPhi}</td>
            <td>${enrollment.trangThaiLop == "Đã khóa lớp" ? "Đã đăng ký" : "Đăng ký mới" }</td>
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
            fetch(`http://localhost:8080/api/dkhp/Enrollment/getEnrollmentByStudentIdAngSemester?studentId=${userId}&hocKy=${semesterId}`)
                .then(response => response.json())
                .then(data => {
                    if (data.status === 'Failed') {
                        console.error(`Error: ${data.message}`);
                        return;
                    }
                    updateEnrollmentTables(data.data);
                })
                .catch(error => console.error('Error fetching enrollment data:', error));
        }
    
        function updateEnrollmentTables(enrollments) {
            const tbodyLocked = document.querySelector('.table-register2 tbody');
            tbodyLocked.innerHTML = ''; // Clear existing rows
    
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
                    <td>${enrollment.hocPhi}</td>
                    <td>${enrollment.trangThaiLop == "Đã khóa lớp" ? "Đã đăng ký" : "Đăng ký mới" }</td>
                    <td>${enrollment.trangThaiLop}</td>
                `;
                tbodyLocked.appendChild(row);
            });
    
            // Show both tables
            const contentLocked = document.querySelector('.content-subject2:nth-of-type(1)');
    
            contentLocked.style.display = 'block';
        }

        function getDetailsInfoTeacher(classId) {
            const containerModel = document.querySelector(".info_teacher")
            fetch("http://localhost:8080/api/admin/teachers/getTeacherDetail/" + classId)
                .then(response => response.json())
                .then(data => {
                    var infoTeacher = data.data;
                    var html = `
                        <h2 class="text-center mb-3">${infoTeacher.hoTen}</h2>
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
                    containerModel.innerHTML = html;
                })
        }

        function getAllScheduleDuplicateOfStudent() {
            const semesterId = document.getElementById('semesterSelect').value;
            const tbodyTable = document.querySelector(".list_schedule")
            fetch(`http://localhost:8080/api/dkhp/Enrollment/checkForDuplicateSchedule?studentId=${encodeURIComponent(userId)}&classId=${encodeURIComponent(selectedClassId)}&hocKi=${encodeURIComponent(semesterId)}`)
                .then(response => response.json())
                .then(data =>{
                    if(data.success == "Failed") alert("Not find schedule duplicated")
                    else {
                        var html = data.data.map((schedule, index) => {
                            return `
                                <tr class="text-center">
                                    <td>${index + 1}</td>
                                    <td>${schedule.maLopHocPhan}</td>
                                    <td>${schedule.tenMonHoc}</td>
                                    <td>${schedule.ngayBatDau}</td>
                                    <td>${schedule.ngayKetThuc}</td>
                                    <td>${schedule.thu}</td>
                                    <td>${schedule.tietHoc}</td>
                                </tr>
                            `
                        })
                        tbodyTable.innerHTML = html.join(" ")
                    }
                } )
        }

function getDetailsInfoTeacher(classId) {
    const containerModel = document.querySelector(".info_teacher")
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
            containerModel.innerHTML = html
        })
}

function getAllScheduleDuplicateOfStudent() {
    const semesterId = document.getElementById('semesterSelect').value;
    const tbodyTable = document.querySelector(".list_schedule")
    fetch(`http://localhost:8080/api/dkhp/Enrollment/checkForDuplicateSchedule?studentId=${encodeURIComponent(userId)}&classId=${encodeURIComponent(selectedClassId)}&hocKi=${encodeURIComponent(semesterId)}`)
        .then(response => response.json())
        .then(data =>{
            if(data.success == "Failed") alert("Not find schedule duplicated")
            else {
                var html = data.data.map((schedule, index) => {
                    return `
                        <tr class="text-center">
                            <td>${index + 1}</td>
                            <td>${schedule.maLopHocPhan}</td>
                            <td>${schedule.tenMonHoc}</td>
                            <td>${schedule.ngayBatDau}</td>
                            <td>${schedule.ngayKetThuc}</td>
                            <td>${schedule.thu}</td>
                            <td>${schedule.tietHoc}</td>
                        </tr>
                    `
                })
                tbodyTable.innerHTML = html.join(" ")
            }
        } )
}
 