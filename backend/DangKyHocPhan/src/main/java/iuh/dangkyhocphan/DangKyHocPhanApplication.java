package iuh.dangkyhocphan;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.CourseOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DangKyHocPhanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangKyHocPhanApplication.class, args);
    }

}
