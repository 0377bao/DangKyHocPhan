package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.CourseOpening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOpeningRepository extends JpaRepository<CourseOpening, Long>{
    CourseOpening findCourseOpeningByhocky(String hocky);
}
