package iuh.dangkyhocphan.respositories;

import iuh.dangkyhocphan.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Enrollment e JOIN e.clazz c JOIN Schedule s on e.clazz.id = s.clazz.id WHERE e.student.id = ?1 AND e.ngayBatDau >= ?2 AND e.ngayKetThuc <= ?3" +
            " order by s.thu, s.tietHoc")
    List<Schedule> findScheduleOfStudent(Long id, LocalDate tuNgay, LocalDate denNgay);

}
