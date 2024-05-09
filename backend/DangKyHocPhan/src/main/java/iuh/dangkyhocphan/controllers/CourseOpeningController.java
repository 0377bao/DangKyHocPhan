package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.CourseOpening;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.repositories.CourseRepository;
import iuh.dangkyhocphan.services.CourseOpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/dkhp/CourseOpening")
public class CourseOpeningController {
    @Autowired
    private CourseOpeningService courseOpeningService;
    // get all course opening in 9 semester
    @GetMapping(path="/")
    List<CourseOpening> getAllCourseOpening() {
        return courseOpeningService.findAll();
    }

    // find list course opening by id_semester
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findCourseOpening(@PathVariable Long id) {
        CourseOpening foundCourseOpening = courseOpeningService.findById(id);
        if(foundCourseOpening == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find course opening id = " + id, "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Query course opening successfully", foundCourseOpening)
        );
    }






}
