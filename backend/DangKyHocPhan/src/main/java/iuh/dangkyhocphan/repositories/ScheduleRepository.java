package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s join Clazz c on s.clazz.id = c.id where c.id = :courseId")
    List<Schedule> findAllScheduleOfCourseByCourseId(Long courseId);
    @Query("SELECT s FROM Enrollment e JOIN e.clazz c JOIN Schedule s on e.clazz.id = s.clazz.id WHERE e.student.id = ?1 AND e.ngayBatDau >= ?2 AND e.ngayKetThuc <= ?3" +
            " order by s.thu, s.tietHoc")
    List<Schedule> findScheduleOfStudent(Long id, LocalDate tuNgay, LocalDate denNgay);
}
