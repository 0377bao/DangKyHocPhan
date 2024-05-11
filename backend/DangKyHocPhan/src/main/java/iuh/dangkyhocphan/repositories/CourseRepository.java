package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.courseOpening.hocky = :sem AND :dept = ANY (select c.khoa from Course c)")
    List<Course> findAllCourseByHockyAndDepartment(String sem, String dept);
}
