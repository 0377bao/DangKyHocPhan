package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.CourseOpening;
import iuh.dangkyhocphan.models.Enrollment;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.repositories.CourseRepository;
import iuh.dangkyhocphan.services.CourseOpeningService;
import iuh.dangkyhocphan.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/dkhp/CourseOpening")
public class CourseOpeningController {
    @Autowired
    private CourseOpeningService courseOpeningService;
    @Autowired
    private EnrollmentService enrollmentService;
    // get all course opening in 9 semester
    @GetMapping(path="/")
    List<CourseOpening> getAllCourseOpening() {
        return courseOpeningService.findAll();
    }

    // find list course opening by id_semester
    @GetMapping("/getCourseOpening")
    ResponseEntity<ResponseObject> findCourseOpening( @RequestParam("studentId") Long id, @RequestParam("hocKy") String hocKy) {
        CourseOpening foundCourseOpening = courseOpeningService.findCourseOpeningByhocky(hocKy);
        List<Enrollment> enrollOfStudent = enrollmentService.findEnrollmentOfStudent(id);
        if(foundCourseOpening == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Query course opening successfully", new ArrayList<>())
            );
        }

        List<Course> courseOpening = foundCourseOpening.getDsKhoaHoc().stream().filter(course -> {
            for(Enrollment enrollment : enrollOfStudent) {
                if(enrollment.getClazz().getCourse().getId() == course.getId()) {
                    return false;
                }
            }
            return true;
        }).toList();

        foundCourseOpening.setDsKhoaHoc(courseOpening);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Query course opening successfully", foundCourseOpening)
        );
    }






}
