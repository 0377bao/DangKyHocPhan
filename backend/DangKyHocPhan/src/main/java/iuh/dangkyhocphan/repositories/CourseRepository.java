package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c.course FROM Clazz c WHERE c.id = ?1")
    public Course getCourseOfClazz(Long id);
}
