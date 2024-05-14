document.addEventListener("DOMContentLoaded", function() {
    function fetchStudentDetails() {
        fetch('http://localhost:8080/api/dkhp/Student/getStudentDetail/1')
            .then(response => response.json())
            .then(data => {
                updateStudentDetails(data);
            })
            .catch(error => console.error('Error fetching student details:', error));
    }

    function updateStudentDetails(student) {
        document.querySelector('.content-between').innerHTML = `
            <p>MSSV: <b>${student.id}</b></p>
            <p>Họ và tên: <b>${student.hoTen}</b></p>
            <p>Giới tính: <b>${student.gioiTinh ? 'Nam' : 'Nữ'}</b></p>
            <p>Ngày sinh: <b>${new Date(student.ngaySinh).toLocaleDateString()}</b></p>
            <p>Nơi sinh: <b>${student.diaChi}</b></p>
        `;

        document.querySelector('.content-right').innerHTML = `
            <p>Lớp: <b>${student.lopHoc}</b></p>
            <p>Khóa: <b>${student.nienKhoa}</b></p>
            <p>Bậc đào tạo: <b>${student.bacDaoTao}</b></p>
            <p>Loại hình đào tạo: <b>${student.loaiHinhDaoTao}</b></p>
            <p>Ngành: <b>${student.chuyenNganh}</b></p>
        `;
    }

    fetchStudentDetails();
});