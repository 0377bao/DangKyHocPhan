package iuh.dangkyhocphan;

import iuh.dangkyhocphan.models.*;

import iuh.dangkyhocphan.repositories.*;
import iuh.dangkyhocphan.services.ClazzService;
import iuh.dangkyhocphan.services.CourseOpeningService;
import iuh.dangkyhocphan.services.EnrollmentService;

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

    public static void main(String[] args) {
        SpringApplication.run(DangKyHocPhanApplication.class, args);
    }

    @Autowired
    private CourseOpeningService service;
    @Autowired
    private CourseRepository repository;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void run(String... args) throws Exception {
        // List<Course> dsKhoaHoc = List.of(
        // new Course( "Nhập môn Tin học", 2, List.of(), 1420000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("kỹ năng làm việc nhóm", 2, List.of(), 1420000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Giáo dục Quốc phòng và An ninh 1", 4, List.of(), 2840000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course( "Toán cao cấp 1", 2, List.of(), 1420000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Giáo dục thể chất 1", 2, List.of(), 1420000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Nhập môn lập trình", 2, List.of(), 1420000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Triết học Mac-Lenin", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Chứng chỉ Tiếng Anh", 0, List.of(), 0, List.of("CNTT"),
        // List.of("SE"), null),
        //
        // new Course("Kỹ thuật lập trình", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Hệ thống máy tính", 4, List.of(), 2840000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Giáo dục quốc phòng và An ninh 2", 3, List.of(), 190000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Kinh tế chính trị Mac-Lenin", 2, List.of(), 1420000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Tếng anh 1", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Toán ứng dụng", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Hàm phức và phép biến đổi Laplace", 3, List.of(), 2130000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Phương pháp tính", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Vật lý đại cương", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Logic học", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Cấu trúc rời rạc", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Cấu trúc dữ liệu và giải thuật", 3, List.of(), 2130000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Hệ cơ sở dữ liệu", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null)
        // );
        //
        // List<Course> ds2 = List.of(
        // new Course("Kỹ thuật lập trình", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Hệ thống máy tính", 4, List.of(), 2840000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Giáo dục quốc phòng và An ninh 2", 3, List.of(), 190000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Kinh tế chính trị Mac-Lenin", 2, List.of(), 1420000,
        // List.of("CNTT"), List.of("SE"), null),
        // );
        // List<Course> ds3 = List.of(
        // new Course("Toán ứng dụng", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Hàm phức và phép biến đổi Laplace", 3, List.of(), 2130000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Phương pháp tính", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Vật lý đại cương", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Logic học", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Cấu trúc rời rạc", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null),
        // new Course("Cấu trúc dữ liệu và giải thuật", 3, List.of(), 2130000,
        // List.of("CNTT"), List.of("SE"), null),
        // new Course("Hệ cơ sở dữ liệu", 3, List.of(), 2130000, List.of("CNTT"),
        // List.of("SE"), null)
        // );
        // repository.saveAll(dsKhoaHoc);
        //
        // List<CourseOpening> dsHk = List.of(
        // new CourseOpening("Học kỳ 1 (2021-2022)", null),
        // new CourseOpening("Học kỳ 2 (2021-2022)", null),
        // new CourseOpening("Học kỳ 3 (2022-2023)", null),
        // new CourseOpening("Học kỳ 4 (2022-2023)", null),
        // new CourseOpening("Học kỳ 5 (2023-2024)", null),
        // new CourseOpening("Học kỳ 6 (2023-2024)", null),
        // new CourseOpening("Học kỳ 7 (2024-2025)", null),
        // new CourseOpening("Học kỳ 8 (2024-2025)", null),
        // new CourseOpening("Học kỳ 9 (2025-2026)", null)
        // );
        // dsHk.forEach(c -> service.save(c));
        // ds
        // CourseOpening courseOpening1 = ;
        // CourseOpening courseOpening2 = new CourseOpening("Học kỳ 3", null);
        // service.save(courseOpening);
        // courseOpening.getDsKhoaHoc().forEach(course -> {
        // course.setCourseOpening(courseOpening);
        // repository.save(course);
        // });

        // CourseOpening c = service.findById(1L);
        // System.out.println(c);
        //
        // CourseOpening c = service.findById(1L);
        // List<Course> ds = repository.findAll();
        // for(int i = 0 ; i < 8; i++) {
        // ds.get(i).setCourseOpening(c);
        // repository.save(ds.get(i));
        // }
        //
        // Administrator a1 = new Administrator("Nguyễn Văn A", LocalDate.now(), true,
        // "1",
        // "0353426938", "hbao27121@gmail.com", "Phú Yên", "Công nghệ thông tin", "Đang
        // làm",
        // 10
        // );
        // administratorRepository.save(a1);

        // Student s1 = new Student("Default", null, true, "0",
        // "0", "default@gmail.com", "", "", "",
        // "", "", "", null, "", "", null
        // );
        // studentRepository.save(s1);
        //
        // Teacher teacher = new Teacher("Nguyễn Văn B", LocalDate.now(), true, "1",
        // "0353426938", "hbao27122@gmail.com", "Quảng ngãi", "Công nghệ thông tin",
        // "Đang làm", "Đại học",
        // LocalDate.now(), true);
        // teacherRepository.save(teacher);
        // Course c1 = repository.findById(1L).get();
        // Course c2 = repository.findById(2L).get();
        // Course c3 = repository.findById(3L).get();
        // Teacher teacher = teacherRepository.findById(1L).get();
        // Administrator a1= administratorRepository.findById(1L).get();
        // Clazz clazz = new Clazz("DHKTPM17C", teacher, 45, 45, "Đã khóa", c1, a1);
        // Clazz clazz1 = new Clazz("DHKTPM17A", teacher, 45, 45, "Đã khóa", c3, a1);
        // Clazz clazz2 = new Clazz("DHKTPM18B", teacher, 45, 45, "Chờ đăng ký", c3,
        // a1);
        // clazzService.save(clazz2);
        // clazzService.save(clazz);
        // clazzService.save(clazz1);
        // Clazz clazz = clazzService.findById(1L);
        // Clazz clazz1 = clazzService.findById(2L);
        // Schedule schedule = new Schedule(Day.MONDAY, "Lý thuyết", "X10.1", "10-12",
        // "Tuần 1-15", clazz);
        // Schedule schedule1 = new Schedule(Day.TUESDAY, "Lý thuyết", "X11.1", "10-12",
        // "Tuần 1-15", clazz1);
        // scheduleRepository.save(schedule);
        // scheduleRepository.save(schedule1);
        // Student s1 = studentRepository.findById(2L).get();
        // System.out.println(s1);
        // Enrollment e = new Enrollment(s1, clazz, null, LocalDate.of(2024, 3,1),
        // LocalDate.now(), "Học kỳ 1 (2021-2022)");
        // Enrollment e1 = new Enrollment(s1, clazz1, null, LocalDate.of(2024, 3,2),
        // LocalDate.now(), "Học kỳ 1 (2021-2022)");
        // enrollmentService.save(e);
        // enrollmentService.save(e1);
        // Enrollment e = enrollmentService.findById(1L);
        // System.out.println(e);

        // Enrollment e = enrollmentService.findById(1L);
        // System.out.println(e);
        // try{
        // boolean result = enrollmentService.deleteEnrollmentByStudentIdAndClazzId(2L,
        // 2L);
        // if(result) System.out.println("Success");
        // else System.out.println("Failed");
        // }catch (Exception ex) {
        // ex.printStackTrace();
        // }

    }

    // }

}
