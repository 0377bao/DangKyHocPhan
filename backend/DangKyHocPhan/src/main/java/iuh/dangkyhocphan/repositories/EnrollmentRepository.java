package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("select e from Enrollment e join Clazz c on e.clazz.id = c.id where c.id = :clazzId")
    Enrollment findEnrollmentByClazzId(Long clazzId);
    @Query("select e from Enrollment e WHERE e.student.id = :studentId AND e.clazz.id = :clazzId")
    Enrollment findEnrollmentByStudentIdAndClazzId(Long studentId, Long clazzId);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = ?1")
    List<Enrollment> findEnrollmentOfStudent(Long id);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = ?1 and e.hocKi = ?2")
    List<Enrollment> getEnrollmentByStudentIdAngSemester(Long id, String hocKi);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = ?1 and e.hocKi = ?2")
    List<Enrollment> findEnrollmentOfStudentBySemester(Long id, String hocKi);

    @Query("SELECT COALESCE(sum(e.clazz.course.soTinChi), 0) FROM Enrollment e WHERE e.student.id = ?1 and e.hocKi = ?2 group by  e.hocKi")
    int totalNumberOfCreditsInTheSemester(Long id, String hocKi);
    @Query("SELECT e.hocKi FROM Enrollment e WHERE e.student.id = ?1 GROUP BY e.hocKi")
    List<String> findSemesterOfStudent(Long id);
}
