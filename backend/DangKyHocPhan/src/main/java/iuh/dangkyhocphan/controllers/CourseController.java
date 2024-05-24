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

import java.util.ArrayList;
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

    @GetMapping("/getCourseInAllSemester")
    public ResponseEntity<ResponseObject> getCourseBySemester() {
        List<Course> courses = courseService.findAll();
        System.out.println(courses.size());
        courses.forEach(System.out::println);
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        for(int i = 1 ; i <= 9 ; i++) {
            List<Map<String, String>> dsTemp = new ArrayList<>();

             for(int j = 0 ; j < courses.size() ; j++) {
                 Map<String, String> courseTemp = new HashMap<>();

                if(courses.get(j).getHocKy().equals("Học kỳ " + i)) {
                    courseTemp.put("id", courses.get(j).getId().toString());
                    courseTemp.put("tenMonHoc", courses.get(j).getTenMonHoc());
                    courseTemp.put("soTinChi", String.valueOf(courses.get(j).getSoTinChi()));
                    courseTemp.put("hocKy", courses.get(j).getHocKy());
                    List<Map<String, String>> dsMonTienQuyet = new ArrayList<>();
                    courses.get(j).getMonTienQuyet().forEach(course -> {
                        Map<String, String> monTienQuyet = new HashMap<>();
                        monTienQuyet.put("id", course.getId().toString());
                        monTienQuyet.put("tenMonHoc", course.getTenMonHoc());
                        dsMonTienQuyet.add(monTienQuyet);
                    });

                    courseTemp.put("monTienQuyet", dsMonTienQuyet.toString());
                    dsTemp.add(courseTemp);
                }
            }
             result.put("Học kỳ " + i, dsTemp);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "List course in all semester", result)
        );
    }


}
