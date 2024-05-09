package iuh.dangkyhocphan;

import iuh.dangkyhocphan.models.*;
import iuh.dangkyhocphan.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DangKyHocPhanApplication implements CommandLineRunner {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ClazzService clazzService;
    @Override
    public void run(String... args) throws Exception {
        Student s1 = new Student("Huỳnh Quốc Bảo", LocalDate.now(), true, "1",
                    "0353426938", "hbao27121@gmail.com", "Quảng ngãi", "Công nghệ thông tin", "Đang học",
                    "Đại học", "Đại trà", "Kỹ thuật phần mềm", LocalDate.now(), "DHKTPM17C", "2021-2026", null
                );
        Administrator a1 = new Administrator("Nguyễn Văn A", LocalDate.now(), true, "1",
                "0353426938", "hbao27121@gmail.com", "Quảng ngãi", "Công nghệ thông tin", "Đang làm",
                1
        );
//        s1 = studentService.save(s1);
//        a1 = administratorService.save(a1);
        Teacher teacher = new Teacher("Nguyễn Văn B", LocalDate.now(), true, "1",
                "0353426938", "hbao27122@gmail.com", "Quảng ngãi", "Công nghệ thông tin", "Đang là", "Đại học",
                 LocalDate.now(), true);
        Course course = new Course( "Lập trình căn bản", 3, List.of(), 110000, List.of("CNTT"), List.of("SE"), null);
        Course course1= new Course( "Công nghệ phần mềm", 2, List.of(), 110000, List.of("CNTT"), List.of("SE"), null);
//        teacher = teacherService.save(teacher);
//        course = courseService.save(course);
//        courseService.save(course1);
//        teacher = teacherService.findById(1L);
//        course = courseService.findById(1L);
//        a1 = administratorService.findById(2L);
        Clazz clazz = new Clazz("DHKTPM17C", teacher, 45, 45, "Đã khóa", course, a1);
//        clazzService.save(clazz);
//        clazz = clazzService.findById(1L);
//        Schedule schedule = new Schedule(Day.MONDAY, "Lý thuyết", "X10.1", "10-12", "Tuần 1-15", clazz);
//        Schedule schedule1 = new Schedule(Day.MONDAY, "Lý thuyết", "H8.02", "1-3", null, clazz);
        Schedule schedule2 = new Schedule(Day.TUESDAY, "Trực tuyến", "Trực tuyến (MS Teams)", "9-10", "Teams: 28zc14v", clazz);
        Schedule schedule3 = new Schedule(Day.WEDNESDAY, "Thực hành", "H9.02", "10-12", null, clazz);
//       scheduleService.save(schedule);
//       scheduleService.save(schedule1);
//        scheduleService.save(schedule2);
//        scheduleService.save(schedule3);
//        s1 = studentService.findById(1L);
//        clazz = clazzService.findById(2L);
//        System.out.println(clazz);
        Enrollment enrollment = new Enrollment(s1, clazz, null, LocalDate.of(2024, 3,1), LocalDate.now(), "Học kỳ 1 (2021-2023)");
//        System.out.println(enrollment);
//        enrollmentService.save(enrollment);
    }

    public static void main(String[] args) {
        SpringApplication.run(DangKyHocPhanApplication.class, args);
    }

}
