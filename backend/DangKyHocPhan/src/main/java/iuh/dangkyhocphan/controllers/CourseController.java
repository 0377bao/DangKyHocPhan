package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/admin/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllCourse(@RequestParam("hocKy") String hocKy, @RequestParam("khoa") String khoa) {
        List<Course> courses = courseService.findAllCourseByHocKyAndKhoa(hocKy, khoa);
        if(courses.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find course in semester belong to" + khoa, "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success","List course in semester belong to" + khoa, courses)
        );

    }


}
