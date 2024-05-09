package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("select e from Enrollment e join Student s on e.student.id = s.id where s.id = :studentId")
    List<Enrollment> findAllEnrollmentByStudentId(Long studentId);
    @Query("select e from Enrollment e join Clazz c on e.clazz.id = c.id where c.id = :clazzId")
    Enrollment findEnrollmentByClazzId(Long clazzId);

    @Query("select e from Enrollment e WHERE e.student.id = :studentId AND e.clazz.id = :clazzId")
    Enrollment findEnrollmentByClazzId(Long studentId, Long clazzId);
}
