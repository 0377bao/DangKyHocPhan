document.addEventListener("DOMContentLoaded", function(){
    getDetailsInfoAdmin();
    getInfoAdministrator()
})

function getDetailsInfoAdmin() {
    const contentLeft = document.querySelector(".content-left")
    const contentRight = document.querySelector(".content-right")
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