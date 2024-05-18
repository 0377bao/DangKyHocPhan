package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.cccd = :cccd")
    Student findStudentByCCCD(String cccd);
}
