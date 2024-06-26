package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("select c.giangVien from Clazz c where c.id = :classId")
    public Teacher findTeacherByClassId(Long classId);
}
