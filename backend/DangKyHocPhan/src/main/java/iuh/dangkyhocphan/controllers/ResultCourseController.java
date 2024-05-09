package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Enrollment;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.services.EnrollmentService;
import iuh.dangkyhocphan.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/dkhp/ResultCourse")
public class ResultCourseController {
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/getResultDetail/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        if(studentService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("faild", "Not found student by id " + id, null)
            );
        }
        List<Enrollment> ds = enrollmentService.findEnrollmentOfStudent(id);
        List<String> dsHocKi = enrollmentService.findSemesterOfStudent(id);
        List<Map> dsKetQua = new ArrayList<>();
        dsHocKi.forEach(e -> {
            Map<String, Object> result = new HashMap<>();
            result.put("hocKi", e);
            List<Map> dsKetQuaMoiKi = new ArrayList<>();
            ds.stream().filter(enrollment -> enrollment.getHocKi().equals(e)).forEach(enrollment -> {
                Map<String, Object> ketQua = new LinkedHashMap<>();
                ketQua.put("maLopHocPhan", enrollment.getClazz().getId());
                ketQua.put("tenMonHoc", enrollment.getClazz().getCourse().getTenMonHoc());
                ketQua.put("giuaKi", enrollment.getResultCourse().getDiemGiuaKi());
                ketQua.put("diemThuongXuyen", enrollment.getResultCourse().getDiemThuongKi());
                ketQua.put("diemThucHanh", enrollment.getResultCourse().getDiemThucHanh());
                ketQua.put("diemCuoiKi", enrollment.getResultCourse().getDiemCuoiKi());
                ketQua.put("diemTongKet", enrollment.getResultCourse().getDiemTongKet());
                ketQua.put("thangDiem4", enrollment.getResultCourse().convertToA4Point());
                ketQua.put("diemChu", enrollment.getResultCourse().convertPointToChart());
                ketQua.put("xepLoai", enrollment.getResultCourse().convertToRank());
                ketQua.put("ghiChu", enrollment.getResultCourse().getGhiChu());
                ketQua.put("trangThai", enrollment.getResultCourse().getTrangThai());
                dsKetQuaMoiKi.add(ketQua);
            });
            result.put("ketQua", dsKetQuaMoiKi);
            dsKetQua.add(result);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Get learning outcomes of student " + id + " success", dsKetQua)
        );
    }
}
