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

import java.util.*;

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

    @GetMapping("/getCourseInAllSemester")
    public ResponseEntity<ResponseObject> getCourseBySemester() {
        List<Course> courses = courseService.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for(int i = 1 ; i <= 9 ; i++) {
            Map<String, Object> itemCourse = new LinkedHashMap<>();
            List<Map<String, Object>> listCourse = new ArrayList<>();

             for(int j = 0 ; j < courses.size() ; j++) {
                if(courses.get(j).getHocKy().equals("Học kỳ " + i)) {
                    Map<String, Object> listCourseItem = new LinkedHashMap<>();
                    List<Map<String, Object>> listCoursePrerequisite = new ArrayList<>();
                    Map<String, Object> listCoursePrerequisiteItem = new LinkedHashMap<>();
                    listCourseItem.put("id", courses.get(j).getId().toString());
                    listCourseItem.put("tenMonHoc", courses.get(j).getTenMonHoc());
                    listCourseItem.put("soTinChi", String.valueOf(courses.get(j).getSoTinChi()));
                    listCourseItem.put("hocKy", courses.get(j).getHocKy());
                    courses.get(j).getMonTienQuyet().forEach(course -> {
                        listCoursePrerequisiteItem.put("id", course.getId().toString());
                        listCoursePrerequisiteItem.put("tenMonHoc", course.getTenMonHoc());
                        listCoursePrerequisite.add(listCoursePrerequisiteItem);
                    });

                    listCourseItem.put("monTienQuyet", listCoursePrerequisite);
                    listCourse.add(listCourseItem);
                }
            }
            itemCourse.put( "hocKy","Học kỳ " + i);
            itemCourse.put( "monHoc",listCourse);
            result.add(itemCourse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "List course in all semester", result)
        );
    }


}
