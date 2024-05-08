package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Clazz;
import iuh.dangkyhocphan.models.Enrollment;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.models.Schedule;
import iuh.dangkyhocphan.services.ClazzService;
import iuh.dangkyhocphan.services.EnrollmentService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="api/dkhp/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private ClazzService clazzService;

    // find list enrollment by student id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getAllEnrollmentByStudentId(@PathVariable Long id) {
        List<Enrollment> enrollmentList = enrollmentService.findAllEnrollmentByStudentId(id);
        List<Map> dsDangKy = new ArrayList<>();
        if(enrollmentList.size() != 0) {
            enrollmentList.forEach(enrollItem -> {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("classId", enrollItem.getClazz().getId());
                result.put("tenLopHocPhan", enrollItem.getClazz().getTenLop());
                result.put("tenGiangVien", enrollItem.getClazz().getGiangVien().getHoTen());
                result.put("tenMonHoc", enrollItem.getClazz().getCourse().getTenMonHoc());
                result.put("soTinChi", enrollItem.getClazz().getCourse().getSoTinChi());
                result.put("hocPhi", enrollItem.getClazz().getCourse().getHocPhi());
                result.put("ngayBatDau", enrollItem.getNgayBatDau());
                result.put("ngayKetThuc", enrollItem.getNgayKetThuc());
                result.put("trangThaiLop", enrollItem.getClazz().getTrangThai());
                dsDangKy.add(result);
            });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Query list enrollment by student id = " + id, dsDangKy)
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Can't find list enrollment by student id = " + id, "")
        );
    }

    // view detail class enrollment by clazzId
    @GetMapping("/detail/{classId}")
    public ResponseEntity<ResponseObject> getDetailInfoClazz(@PathVariable Long classId) {
        boolean foundClazz = clazzService.existsById(classId);

        List<Map> dsLichHoc = new ArrayList<>();
        if(foundClazz) {
            List<Schedule> scheduleList = clazzService.findScheduleOfClazz(classId);
            scheduleList.forEach(schedule -> {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("thu", schedule.getThu());
                result.put("loaiLich", schedule.getLoaiLich());
                result.put("phongHoc", schedule.getPhongHoc());
                result.put("tietHoc", schedule.getTietHoc());
                result.put("ghiChu", schedule.getGhiChu());
                result.put("tenGiangVien", schedule.getClazz().getGiangVien().getHoTen());
                dsLichHoc.add(result);
            });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Query class detail successfully", dsLichHoc)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Not found schedule of class with id = " + classId, "")
        );
    }

    // cancel enrollment by id



}
