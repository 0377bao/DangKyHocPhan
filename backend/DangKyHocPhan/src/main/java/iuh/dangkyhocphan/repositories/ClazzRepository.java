package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
    @Query("SELECT c FROM Clazz c WHERE c.course.id = ?1")
    Clazz findClazzByCourseId(Long courseId);
}
