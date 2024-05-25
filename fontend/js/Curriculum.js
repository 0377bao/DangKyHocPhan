document.addEventListener("DOMContentLoaded", function() {
    fetchCourseData();
});

function fetchCourseData() {
    fetch('http://localhost:8080/api/admin/courses/getCourseInAllSemester')
        .then(response => response.json())
        .then(data => {
            updateCoursesTable(data.data);
        })
        .catch(error => console.error('Error fetching course data:', error));
}

function updateCoursesTable(data) {
    const tbody = document.querySelector('.table-register tbody');
    tbody.innerHTML = ''; // Clear existing rows before populating new data

    data.forEach((semester) => {
        // Add semester header
        const semesterRow = document.createElement('tr');
        semesterRow.innerHTML = `<td colspan="8" style="color: rgb(235, 123, 33); font-weight: bold;">${semester.hocKy}</td>`;
        tbody.appendChild(semesterRow);

        // Check if there are courses listed for the semester
        if (semester.monHoc && semester.monHoc.length > 0) {
            semester.monHoc.forEach((course, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td style="text-align: center;">${index + 1}</td>
                    <td style="text-align: center;">${course.id}</td>
                    <td style="text-align: center;">${course.tenMonHoc}</td>
                    <td style="text-align: center;">${course.id}</td> <!-- Assuming Mã học phần could be the same as course id -->
                    <td style="text-align: center; max-width: 260px">${course.monTienQuyet.map(item => item.id).join(', ') || ''}</td>
                    <td style="text-align: center;">${course.soTinChi}</td>
                `;
                tbody.appendChild(row);
            });
        } else {
            // Add a row indicating no courses are available for the semester
            const noCoursesRow = document.createElement('tr');
            noCoursesRow.innerHTML = `<td colspan="8">The program has not been updated</td>`;
            tbody.appendChild(noCoursesRow);
        }
    });
}