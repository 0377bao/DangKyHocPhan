package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Day;
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
    @Query("SELECT s FROM Enrollment e JOIN e.clazz c JOIN Schedule s on e.clazz.id = s.clazz.id WHERE e.student.id = ?1 AND e.ngayBatDau <= ?2 AND e.ngayKetThuc >= ?3" +
            " order by s.thu, s.tietHoc")
    List<Schedule> findScheduleOfStudent(Long id, LocalDate tuNgay, LocalDate denNgay);

    @Query("select e from Schedule e join Clazz c on e.clazz.id = c.id join Course co on c.course.id = co.id where co.courseOpening.hocky = :hocky and e.thu = :thu and e.tietHoc = :tietHoc")
    Schedule checkScheduleDuplicateForActionCreateClazz(String hocky, Day thu, String tietHoc);

    @Query("SELECT s FROM Enrollment e JOIN Schedule s on e.clazz = s.clazz WHERE e.student.id = ?1 and e.hocKi = ?2")
    List<Schedule> findScheduleOfStudentBySemester(Long id, String hocKi);
    @Query("SELECT s FROM Schedule s WHERE s.clazz.id = ?1")
    List<Schedule> findScheduleByClazz(Long clazzId);

}
