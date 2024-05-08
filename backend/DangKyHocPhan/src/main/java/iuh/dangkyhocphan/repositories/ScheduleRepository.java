package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s join Clazz c on s.clazz.id = c.id where c.id = :courseId")
    List<Schedule> findAllScheduleOfCourseByCourseId(Long courseId);
}
