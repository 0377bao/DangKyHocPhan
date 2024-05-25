package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.*;
import iuh.dangkyhocphan.services.*;

import java.util.Map;
import iuh.dangkyhocphan.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/dkhp/Enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ResultCourseService resultCourseService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StudentService studentService;

    // find list enrollment by student id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getAllEnrollmentByStudentId(@PathVariable Long id) {
        List<Enrollment> enrollmentList = enrollmentService.findEnrollmentOfStudent(id);
        List<Map> dsDangKy = new ArrayList<>();
        if (enrollmentList.size() != 0) {
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
    @GetMapping("/getEnrollmentByStudentIdAngSemester")
    public ResponseEntity<ResponseObject> getEnrollmentByStudentIdAngSemester(@RequestParam("studentId") Long id, @RequestParam("hocKy") String hocKy) {
        List<Enrollment> enrollmentList = enrollmentService.getEnrollmentByStudentIdAngSemester(id, hocKy);
        List<Map> dsDangKy = new ArrayList<>();
        if (enrollmentList.size() != 0) {
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

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Query list enrollment by student id = " + id, new ArrayList<>())
        );
    }

    // view detail class enrollment by clazzId
    @GetMapping("/detail/{classId}")
    public ResponseEntity<ResponseObject> getDetailInfoClazz(@PathVariable Long classId) {
        boolean foundClazz = clazzService.existsById(classId);

        List<Map> dsLichHoc = new ArrayList<>();
        if (foundClazz) {
            List<Schedule> scheduleList = clazzService.findScheduleOfClazz(classId);
            scheduleList.forEach(schedule -> {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("maLopHocPhan", schedule.getClazz().getId());
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
  
    @PostMapping("/createEnrollment")
    public ResponseEntity<ResponseObject> createEnrollment(@RequestBody EnrollmentCreateDTO enrollment) {
        if(enrollment.getNgayBatDau() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("faild", "Start date cannot be empty", null)
            );
        }
        if(enrollment.getNgayKetThuc() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("faild", "End date cannot be empty", null)
            );
        }
        if(enrollment.getNgayBatDau().isAfter(enrollment.getNgayKetThuc())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("faild", "The start date cannot be after the end date", null)
            );
        }
        if(enrollment.getHocKi() == null || enrollment.getHocKi().trim().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("faild", "Semester cannot be empty", null)
            );
        }
        Student st = studentService.findById(enrollment.getStudent_id());
        Clazz cl = clazzService.findById(enrollment.getClass_id());
        if(st == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("faild", "Not found student by id " + enrollment.getStudent_id(), null)
            );
        }
        if(cl == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("faild", "Not found class by id " + enrollment.getClass_id(), null)
            );
        }
        List<Course> dsMonTienQuyet = courseService.getCourseOfClazz(enrollment.getClass_id()).getMonTienQuyet();
        String checkRequiredCourse = checkCourseRequisiteSubjects(dsMonTienQuyet, enrollment.getStudent_id());
        if (!checkRequiredCourse.equals("passed")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("Failed", "Create enrollment fail because course " + checkRequiredCourse + " has not been completed", "")
            );
        }
        if(checkForDuplicateScheduleMethod(enrollment.getStudent_id(), enrollment.getClass_id(), enrollment.getHocKi()).size() != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("Failed", "Create enrollment fail because the class schedule clashed", "")
            );
        }
        if(cl.getSiSoHienTai() == cl.getSiSoToiDa()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("Failed", "Create enrollment fail because class full slot ", "")
            );
        }
        if(enrollmentService.totalNumberOfCreditsInTheSemester(enrollment.getStudent_id(), enrollment.getHocKi()) + cl.getCourse().getSoTinChi() > 30) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("Failed", "Create enrollment fail because the number of credits exceeds the limit 30", "")
            );
        }
        Enrollment add = new Enrollment(st, cl, null, enrollment.getNgayBatDau(), enrollment.getNgayKetThuc(), enrollment.getHocKi());
        Enrollment isCreated = enrollmentService.save(add);
        if(isCreated != null) {
            cl.setSiSoHienTai(cl.getSiSoHienTai() + 1);
            clazzService.save(cl);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("classId", isCreated.getClazz().getId());
            result.put("tenLopHocPhan", isCreated.getClazz().getTenLop());
            result.put("tenGiangVien", isCreated.getClazz().getGiangVien().getHoTen());
            result.put("tenMonHoc", isCreated.getClazz().getCourse().getTenMonHoc());
            result.put("soTinChi", isCreated.getClazz().getCourse().getSoTinChi());
            result.put("hocPhi", isCreated.getClazz().getCourse().getHocPhi());
            result.put("ngayBatDau", isCreated.getNgayBatDau());
            result.put("ngayKetThuc", isCreated.getNgayKetThuc());
            result.put("trangThaiLop", isCreated.getClazz().getTrangThai());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Create enrollment successfully", result)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("Failed", "Can't create enrollment", "")
        );
    }
    // cancel enrollment by id
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteEnrollment(@RequestParam("studentId") Long studentId, @RequestParam("classId") Long classId) {
        // check student and class were exist
        boolean checkStudent = studentService.existsById(studentId);
        boolean checkClazz = clazzService.existsById(classId);
        if(!checkStudent){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find student with id = " + studentId, "")
            );
        }
        if(!checkClazz) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find class with id = " + classId, "")
            );
        }
        // check status class
        Clazz currentClazz = clazzService.findById(classId);
        if(currentClazz.getTrangThai().equalsIgnoreCase("Đã khóa")) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Class was blocked so you can't delete enrollment", "")
            );
        }else {
            boolean result = enrollmentService.deleteEnrollmentByStudentIdAndClazzId(studentId, classId);
            if(result) {
                try{
                    currentClazz.setSiSoHienTai(currentClazz.getSiSoHienTai() - 1);
                    clazzService.save(currentClazz);
                }catch (Exception e){
                    throw new RuntimeException("Can't update amount of student for class with id = " + classId);
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Success", "Delete enrollment successfully", "")
                );
             }
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("Failed", "Can't delete enrollment with id = " + classId, "")
        );
    }

    @GetMapping("/checkForDuplicateSchedule")
    public ResponseEntity<ResponseObject> checkForDuplicateSchedule(@RequestParam("studentId") Long studentid, @RequestParam("classId") Long classId, @RequestParam("hocKi") String hocKi) {
        if(studentService.findById(studentid) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("faild", "Not found student by id " + studentid, null)
            );
        }
        List<Schedule> ds = checkForDuplicateScheduleMethod(studentid, classId, hocKi);
        List<Map> dsLop = new ArrayList<>();
        ds.forEach(schedule -> {
            Map<String, Object> result = new HashMap<>();
            result.put("tenMonHoc", schedule.getClazz().getCourse().getTenMonHoc());
            result.put("tenLop", schedule.getClazz().getTenLop());
            result.put("maLopHocPhan", schedule.getClazz().getId());
            result.put("tietHoc", schedule.getTietHoc());
            result.put("thu", schedule.getThu());
            result.put("phongHoc", schedule.getPhongHoc());
            result.put("tenGiangVien", schedule.getClazz().getGiangVien().getHoTen());
            result.put("loaiLich", schedule.getLoaiLich());
            result.put("ngayBatDau", schedule.getClazz().getNgayBatDau());
            result.put("ngayKetThuc", schedule.getClazz().getNgayKetThuc());
            if(schedule.getLoaiLich().equals("Trực tuyến"))
                result.put("ghiChu", schedule.getGhiChu());
            dsLop.add(result);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Check duplicate schedule successfully", dsLop)
        );
    }

    public List<Schedule> checkForDuplicateScheduleMethod(Long studentid, Long classId, String hocKi) {
        List<Schedule> dsKetQua = new ArrayList<>();
        List<Schedule> dsLich = scheduleService.findScheduleOfStudentBySemester(studentid, hocKi);
        List<Schedule> dsLichHoc = clazzService.findScheduleOfClazz(classId);
        dsLichHoc.forEach(schedule -> {
            for (Schedule s : dsLich) {
                List<String> tietKiemTra = List.of(schedule.getTietHoc().split("-"));
                List<String> tiet = List.of(s.getTietHoc().split("-"));
                if(s.getThu().equals(schedule.getThu()) && checkLesson(tietKiemTra, tiet)) {
                    dsKetQua.add(s);
                }
            }
        });
        return dsKetQua;
    }

    public boolean checkLesson(List<String> tietKiemTra, List<String> tiet) {
        if(Integer.parseInt(tiet.get(0)) <= Integer.parseInt(tietKiemTra.get(0)) && Integer.parseInt(tiet.get(1)) > Integer.parseInt(tietKiemTra.get(0)))
            return true;
        else if(Integer.parseInt(tiet.get(0)) < Integer.parseInt(tietKiemTra.get(1)) && Integer.parseInt(tiet.get(1)) >= Integer.parseInt(tietKiemTra.get(1)))
            return true;
        return false;
    }

    public String checkCourseRequisiteSubjects(List<Course> dsMonTienQuyet, Long id) {
        for (Course course : dsMonTienQuyet) {
            ResultCourse find = resultCourseService.getResultCourseOfStudent(id, course.getId());
            if (find == null || !find.getTrangThai().equals("Đạt")) {
                return course.getTenMonHoc();
            }
        }
        return "passed";
    }
}
