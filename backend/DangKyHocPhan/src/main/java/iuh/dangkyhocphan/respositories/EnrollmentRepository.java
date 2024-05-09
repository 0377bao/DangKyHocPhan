package iuh.dangkyhocphan.respositories;

import iuh.dangkyhocphan.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT e FROM Enrollment e WHERE e.student.id = ?1")
    List<Enrollment> findEnrollmentOfStudent(Long id);
    @Query("SELECT e.hocKi FROM Enrollment e WHERE e.student.id = ?1 GROUP BY e.hocKi")
    List<String> findSemesterOfStudent(Long id);
}
