package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.models.Schedule;
import iuh.dangkyhocphan.services.ScheduleService;
import iuh.dangkyhocphan.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/dkhp/Schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/getScheduleOfStudent")
    ResponseEntity<ResponseObject> findScheduleOfStudent(@RequestParam("id") Long id,
                                                        @RequestParam("tuNgay") LocalDate tuNgay,
                                                        @RequestParam(value = "denNgay") LocalDate denNgay) {
        if(studentService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("faild", "Not found student by id " + id, null)
            );
        }
        if(tuNgay.isAfter(denNgay)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("faild", "The start date cannot be after the end date", null)
            );
        }
        List<Schedule> foundSchedules = scheduleService.findScheduleOfStudent(id, tuNgay, denNgay);
        List<Map> dsLop = new ArrayList<>();
        foundSchedules.forEach(schedule -> {
            Map<String, Object> result = new HashMap<>();
            result.put("tenMonHoc", schedule.getClazz().getCourse().getTenMonHoc());
            result.put("tenLop", schedule.getClazz().getTenLop());
            result.put("maLopHocPhan", schedule.getClazz().getId());
            result.put("tietHoc", schedule.getTietHoc());
            result.put("thu", schedule.getThu());
            result.put("phongHoc", schedule.getPhongHoc());
            result.put("tenGiangVien", schedule.getClazz().getGiangVien().getHoTen());
            result.put("loaiLich", schedule.getLoaiLich());
            if(schedule.getLoaiLich().equals("Trực tuyến"))
                result.put("ghiChu", schedule.getGhiChu());
            dsLop.add(result);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Get schedule of student " + id + " success", dsLop)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Schedule> foundSchedule = Optional.ofNullable(scheduleService.findById(id));
        return foundSchedule.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Get schedule by id success", foundSchedule)
        ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("fail", "Cannot find schedule with id: " + id, null)
        );
    }
}
