package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
    @Query("SELECT c FROM Clazz c WHERE c.course.id = ?1")
    Clazz findClazzByCourseId(Long courseId);
    @Query("SELECT c FROM Clazz c WHERE c.course.id = :courseId AND c.trangThai = :status")
    List<Clazz> findClazzByCourseIdAndStatus(Long courseId, String status);
}
