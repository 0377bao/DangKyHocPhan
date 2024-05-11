package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.ResultCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultCourseRepository extends JpaRepository<ResultCourse, Long> {
    @Query("SELECT e.resultCourse FROM Enrollment e WHERE e.student.id = ?1 And e.clazz.course.id = ?2")
    public ResultCourse getResultCourseOfStudent(Long id, Long courseId);
}
