package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("select e from Enrollment e join Clazz c on e.clazz.id = c.id where c.id = :clazzId")
    Enrollment findEnrollmentByClazzIds(Long clazzId);

    @Query("select e from Enrollment e WHERE e.student.id = :studentId AND e.clazz.id = :clazzId")
    Enrollment findEnrollmentByClazzId(Long studentId, Long clazzId);
    @Query("SELECT e FROM Enrollment e WHERE e.student.id = ?1")
    List<Enrollment> findEnrollmentOfStudent(Long id);
    @Query("SELECT e.hocKi FROM Enrollment e WHERE e.student.id = ?1 GROUP BY e.hocKi")
    List<String> findSemesterOfStudent(Long id);
}
