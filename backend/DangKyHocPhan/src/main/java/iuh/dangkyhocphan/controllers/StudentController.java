package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Student;
import iuh.dangkyhocphan.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/dkhp/Student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/getStudentDetail/{id}")
    Student getStudentDetail(@PathVariable Long id) {
        return studentService.findById(id);
    }
}
