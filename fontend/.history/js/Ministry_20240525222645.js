let teachers = [];
let classData = [];

document.addEventListener("DOMContentLoaded", function () {
    fetchTeachers();
    fetchClassData();
    getInfoAdministrator();
    const leftContent = document.querySelector(".content-left");
    const rightContent = document.querySelector(".content-right");
    leftContent && rightContent ? getDetailsInfoAdmin():"";
});

function logout() {
    window.location.href = "LoginMinistry.html";
}

function fetchTeachers() {
    fetch("http://localhost:8080/api/admin/teachers")
        .then((response) => response.json())
        .then((data) => {
            if (data.status === "Success") {
                teachers = data.data;
                populateTeachers(teachers);
            } else {
                console.error("Failed to fetch teachers:", data.message);
            }
        })
        .catch((error) => {
            console.error("Error fetching teachers:", error);
        });
}

function populateTeachers(teachers) {
    const teacherSelect = document.createElement("select");
    teacherSelect.id = "teacherSelect";
    teachers.forEach((teacher) => {
        const option = document.createElement("option");
        option.value = teacher.maGiangVien;
        option.textContent = teacher.tenGiangVien;
        teacherSelect.appendChild(option);
    });
    document.querySelector(".content-register").appendChild(teacherSelect);
}

function fetchCourses() {
    const semesterText = document.getElementById("semesterSelect").value;
    fetch(`http://localhost:8080/api/admin/courses?hocKy=${encodeURIComponent(semesterText)}&khoa=CNTT`)
        .then((response) => response.json())
        .then((data) => {
            if (data.status === "Success") {
                populateCourses(data.data);
            } else {
                console.error("Failed to fetch courses:", data.message);
            }
        })
        .catch((error) => {
            console.error("Error fetching courses:", error);
        });
}

function populateCourses(courses) {
    const classInfoTable = document.getElementById("classInfoTable");
    classInfoTable.innerHTML = ""; // Clear previous data
    const classScheduleTable = document.getElementById("classScheduleTable");
    classScheduleTable.innerHTML = ""; // Clear previous data

    courses.forEach((course, index) => {
        const teacher = teachers[index % teachers.length]; // Assign teachers cyclically or based on any logic
        const classInfoRow = document.createElement("tr");
        classInfoRow.innerHTML = `
        <td><input type="radio"  name="dk" ></td>
        <td>${index + 1}</td>
        <td>1</td>
        <td>${document.getElementById("semesterSelect").value}</td>
        <td>${course.id}</td>
        <td>${course.tenMonHoc}</td>
        <td>${course.soTinChi}</td>
        <td>${teacher.maGiangVien}</td>
        <td>${teacher.tenGiangVien}</td>
    `;
        classInfoTable.appendChild(classInfoRow);

        const classScheduleRow = document.createElement("tr");
        classScheduleRow.innerHTML = `
        <td>${course.id}</td>
        <td>${course.tenMonHoc}</td>
        <td>2021/08/10</td>
        <td>2022/05/30</td>
        <td>-</td>
        <td>-</td>
        <td>0</td>
        <td>50</td>
        <td>Chờ đăng ký</td>
    `;
        classScheduleTable.appendChild(classScheduleRow);
    });
}

function fetchClassData() {
    fetch("http://localhost:8080/api/admin/clazz/")
        .then((response) => response.json())
        .then((data) => {
            if (data.status === "Success") {
                classData = data.data;
                // populateClassData(classData);
            } else {
                console.error("Failed to fetch class data:", data.message);
            }
        })
        .catch((error) => {
            console.error("Error fetching class data:", error);
        });
}

function getInfoAdministrator() {
    const infoPersonal = document.querySelector(".menu-personal");
    const idAdmin = JSON.parse(localStorage.getItem("loggedInUser"));
    const url = "http://localhost:8080/api/admin/Info/" + idAdmin.magv;
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            var admin = data.data;
            let html = `
                <p>Xin chào!</p>
                <p><h3>${admin.hoTen}</h3></p>
                <p>Giới tính:ㅤㅤㅤㅤ${admin.gioiTinh ? "Nam" : "Nữ"}</p>
                <p>SĐT:ㅤㅤㅤㅤㅤㅤ${admin.sdt}</p>
                <p>Chức vụ:ㅤㅤㅤㅤGiáo vụ</p>
                <button type="submit" onclick="logout()">Đăng xuất</button>
            `;
            infoPersonal.innerHTML += html;
        })
        .catch((err) => console.log(err));
}

function getDetailsInfoAdmin() {
    const contentLeft = document.querySelector(".content_left")
    const contentRight = document.querySelector(".content_right")
    const idAdmin = JSON.parse(localStorage.getItem("loggedInUser"))
    fetch("http://localhost:8080/api/admin/Info/" + idAdmin.magv)
        .then(response => response.json())
        .then(data => {
            var html_content_left_homeMinistry = `
                <p>Vị trí công tác: <b id="teacherDepartment">${data.data.khoa}</b></p>
                <p>Chức vụ: <b id="teacherRole">Giáo vụ</b></p>
                <p>Gmail: <b id="teacherEmail">${data.data.email}</b></p>
                <p>Địa chỉ: <b id="teacherAddress">${data.data.diaChi}</b></p>
            `

            var html_content_right_homeMinistry = `
                <p>Chức danh: <b id="teacherDegree">Đại học</b></p>
                <p>Ngoại ngữ: <b id="teacherLanguage">English</b></p>
                <p>Chuyên ngành: <b id="teacherMajor">${data.data.khoa.slice(5, (data.data.khoa).length)}</b></p>
                <p>Cơ sở: <b id="teacherCampus">Cơ sở 1 (Thành phố Hồ Chí Minh)</b></p>
            `
            contentLeft.innerHTML += html_content_left_homeMinistry
            contentRight.innerHTML += html_content_right_homeMinistry
        })
}

function logout() {
    localStorage.removeItem("loggedInUser");
    window.location.href = "LoginMinistry.html";
}