document.addEventListener("DOMContentLoaded", function() {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('User not logged in');
        window.location.href = "Home.html";
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
        // Updating personal menu section
        const menuPersonal = document.querySelector('.menu-personal');
        menuPersonal.innerHTML = `
            <p>Xin chào!</p>
            <p><h2>${student.hoTen}</h2></p>
            <p>Giới tính: ${student.gioiTinh ? 'Nam' : 'Nữ'}</p>
            <p>MSSV: ${student.id}</p>
            <p>Trạng thái: ${student.trangThai}</p>
            <button type="submit" onclick="login()">Đăng xuất</button>
        `;

        // Updating content-left section
        const contentLeft = document.querySelector('.content-left');
        contentLeft.innerHTML = `
            <p>Khóa: <b>${student.nienKhoa}</b></p>
            <p>Bậc đào tạo: <b>${student.bacDaoTao}</b></p>
            <p>Ngành: <b>${student.chuyenNganh}</b></p>
            <p>Khoa: <b>${student.khoa}</b></p>
        `;

        // Updating content-right section
        const contentRight = document.querySelector('.content-right');
        contentRight.innerHTML = `
            <p>Lớp: <b>${student.lopHoc}</b></p>
            <p>Loại hình đào tạo: <b>${student.loaiHinhDaoTao}</b></p>
            <p>Chuyên ngành: <b>${student.chuyenNganh}</b></p>
            <p>Cơ sở: <b>Cơ sở 1 (Thành phố Hồ Chí Minh)</b></p>
        `;
    }

    fetchStudentDetails();
});