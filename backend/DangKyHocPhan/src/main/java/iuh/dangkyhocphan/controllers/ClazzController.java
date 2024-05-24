package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.*;
import iuh.dangkyhocphan.services.*;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/admin/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CourseOpeningService courseOpeningService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/")
    public ResponseEntity<ResponseObject> getAllClazz(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Query class successfully", clazzService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getClazzById(@PathVariable Long id){
        Clazz clazz = clazzService.findClazzByCourseId(id);
        Map<String, Object> result = new HashMap<>();
        if(clazz != null){
            result.put("maLop", clazz.getId());
            result.put("tenLop", clazz.getTenLop());
            result.put("maMonHoc", clazz.getCourse().getId());
            result.put("tenMonHoc", clazz.getCourse().getTenMonHoc());
            result.put("maGiangVien", clazz.getGiangVien().getId());
            result.put("tenGiangVien", clazz.getGiangVien().getHoTen());
            result.put("soTinChi",clazz.getCourse().getSoTinChi());
            result.put("siSoHienTai", clazz.getSiSoHienTai());
            result.put("siSoToiDa", clazz.getSiSoToiDa());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Query class by id = " + id, result)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Can't find class by id = " + id, "")
        );
    }

    @PostMapping("/createClazz")
    public ResponseEntity<ResponseObject> createClazz(@RequestBody ClazzDTO clazz){
        CourseOpening courseOpening = courseOpeningService.findById(clazz.getSemesterId());
        if(courseOpening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find course opening id = " + clazz.getSemesterId(), null)
            );
        }
        Clazz foundClazz = clazzService.findById(clazz.getClassId());
        if(foundClazz == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Class id = " + clazz.getClassId() + " is not exits", null)
            );
        }
        if(clazz.getTenLop().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Class name is empty", "")
            );
        }
        Teacher teacher = teacherService.findById(clazz.getTeacherId());
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find teacher id = " + clazz.getTeacherId(), null)
            );
        }
        if(clazz.getTenGiangVien().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Teacher name is empty", "")
            );
        }
        Course course = courseService.findById(clazz.getCourseId());
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find course id = " + clazz.getCourseId(), null)
            );
        }
        if(clazz.getTenMonHoc().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Course name is empty", "")
            );
        }
        Administrator administrator = administratorService.findById(clazz.getAdministratorId());
        if (administrator == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find administrator id = " + clazz.getAdministratorId(), null)
            );
        }

        if(clazz.getSoTinChi() < 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("Failed", "Number of credits must be greater than 0", "")
            );
        }

        if(clazz.getNgayBatDau() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Start date is empty", "")
            );
        }
        if(clazz.getNgayKetThuc() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "End date is empty", "")
            );
        }
        if(clazz.getNgayBatDau().isAfter(clazz.getNgayKetThuc())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Start date must be before end date", "")
            );
        }
        if(clazz.getNgayBatDau().equals(clazz.getNgayKetThuc())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Start date must be different from end date", "")
            );
        }

        if(clazz.getLichHocThucHanh().size() == 0 && clazz.getLichHocLyThuyet().size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Practice schedule or theory schedule is empty", "")
            );
        }

        for (ScheduleDTO schedule : clazz.getLichHocLyThuyet()) {
            if(schedule.getThu().equals("") || schedule.getTietHoc().equals("") || schedule.getPhongHoc().equals("") || schedule.getLoaiLich().equals("")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("Failed", "Theory schedule is empty", "")
                );
            }
        }

        for (ScheduleDTO schedule : clazz.getLichHocThucHanh()) {
            if(schedule.getThu().equals("") || schedule.getTietHoc().equals("") || schedule.getPhongHoc().equals("") || schedule.getLoaiLich().equals("")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("Failed", "Practice schedule is empty", "")
                );
            }
        }

        // check lich hoc ly thuyet
        for (ScheduleDTO schedule : clazz.getLichHocLyThuyet()) {
            boolean isDuplicate = scheduleService.checkScheduleDuplicateForActionCreateClazz(courseOpening.getHocky(), schedule.getThu(), schedule.getTietHoc());
            if(isDuplicate){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("Failed", "Duplicate theoretical class schedule", "")
                );
            }
        }

        // check lich hoc thuc hanh
        for (ScheduleDTO schedule : clazz.getLichHocThucHanh()) {
            boolean isDuplicate = scheduleService.checkScheduleDuplicateForActionCreateClazz(courseOpening.getHocky(), schedule.getThu(), schedule.getTietHoc());
            if(isDuplicate){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("Failed", "Duplicate practice schedules", "")
                );
            }
        }

        if(clazz.getSiSoHienTai() > clazz.getSiSoToiDa()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Current number of students must be less than or equal to the maximum number of students", "")
            );
        }
        if (clazz.getSiSoHienTai() < 0 || clazz.getSiSoToiDa() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Current number of students and maximum number of students must be greater than 0", "")
            );
        }
        if(clazz.getTrangThai().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Status is empty", "")
            );
        }
        Student studentDefault = studentService.findStudentByCCCD("0");
        Enrollment enrollment = new Enrollment(studentDefault, foundClazz, null,clazz.getNgayBatDau(),clazz.getNgayKetThuc(), courseOpening.getHocky());
        enrollmentService.save(enrollment);

        if(enrollment != null){
            foundClazz.setTenLop(clazz.getTenLop());
            foundClazz.setCourse(course);
            course.setCourseOpening(courseOpening);
            foundClazz.setGiangVien(teacher);
            foundClazz.setAdministrator(administrator);
            foundClazz.setSiSoHienTai(clazz.getSiSoHienTai());
            foundClazz.setSiSoToiDa(clazz.getSiSoToiDa());
            foundClazz.setTrangThai(clazz.getTrangThai());
            courseService.save(course);
            clazzService.save(foundClazz);
            clazz.getLichHocLyThuyet().forEach(scheduleLT -> {
                Schedule schedule = new Schedule();
                schedule.setThu(scheduleLT.getThu());
                schedule.setLoaiLich(scheduleLT.getLoaiLich());
                schedule.setPhongHoc(scheduleLT.getPhongHoc());
                schedule.setTietHoc(scheduleLT.getTietHoc());
                schedule.setGhiChu(scheduleLT.getGhiChu());
                schedule.setClazz(foundClazz);
                scheduleService.save(schedule);
            });

            clazz.getLichHocThucHanh().forEach(scheduleTH -> {
                Schedule schedule = new Schedule();
                schedule.setThu(scheduleTH.getThu());
                schedule.setLoaiLich(scheduleTH.getLoaiLich());
                schedule.setPhongHoc(scheduleTH.getPhongHoc());
                schedule.setTietHoc(scheduleTH.getTietHoc());
                schedule.setGhiChu(scheduleTH.getGhiChu());
                schedule.setClazz(foundClazz);
                scheduleService.save(schedule);
            });
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("Success", "Create class successfully", clazz)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("Failed", "Create class failed", "")
        );

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateClazz(@RequestBody Clazz clazz, @PathVariable Long id){
        Clazz foundedClass = clazzService.findById(id);
        if(foundedClass != null){
            foundedClass.setAdministrator(clazz.getAdministrator());
            foundedClass.setCourse(clazz.getCourse());
            foundedClass.setGiangVien(clazz.getGiangVien());
            foundedClass.setTenLop(clazz.getTenLop());
            foundedClass.setSiSoHienTai(clazz.getSiSoHienTai());
            foundedClass.setSiSoToiDa(clazz.getSiSoToiDa());
            clazzService.save(foundedClass);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find class id = " + id, "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Update class successfully", foundedClass)
        );

    }

        @GetMapping("/clazzPlan")
    public ResponseEntity<ResponseObject> getAllClazzPlanOfCourse(@RequestParam("courseId") Long courseId, @RequestParam("status") String status){
        List<Clazz> clazzList = clazzService.findClazzByCourseIdAndStatus(courseId, status);
        List<Map> dsClazz = new ArrayList<>();
        if(clazzList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find class plan belong to course id = " + courseId, "")
            );
        }
        clazzList.forEach(clazz -> {
            Map<String, Object> result = new HashMap<>();
            result.put("maLop", clazz.getId());
            result.put("maMonHoc", clazz.getCourse().getId());
            result.put("tenMonHoc", clazz.getCourse().getTenMonHoc());
            result.put("tenLop", clazz.getTenLop());
            result.put("siSoToiDa", clazz.getSiSoToiDa());
            result.put("siSoHienTai", clazz.getSiSoHienTai());
            result.put("trangThai", clazz.getTrangThai());
            dsClazz.add(result);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Query class plan successfully", dsClazz)
        );
    }
}
