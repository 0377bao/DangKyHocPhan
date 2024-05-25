package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.models.Teacher;
import iuh.dangkyhocphan.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/admin/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllTeacher() {
        List<Teacher> teacherList = teacherService.findAll();
        List<Map> dsGiangVien = new ArrayList<>();
        if (teacherList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find any teacher", "")
            );
        }
        teacherList.forEach(teacher -> {
            Map<String, Object> result = new HashMap<>();
            result.put("maGiangVien", teacher.getId());
            result.put("tenGiangVien", teacher.getHoTen());
            dsGiangVien.add(result);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "List of all teachers", dsGiangVien)
        );
    }
    @GetMapping("/getTeacherDetail/{classId}")
    public ResponseEntity<ResponseObject> getTeacherDetailByClassId(@PathVariable Long classId) {
        Teacher teacher = teacherService.findTeacherByClassId(classId);
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find teacher by class id " + classId, "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Get teacher detail by class id " + classId + " success", teacher)
        );
    }
}
