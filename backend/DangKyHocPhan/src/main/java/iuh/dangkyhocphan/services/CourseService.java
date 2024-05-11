package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements IService<Course, Long>{
    @Autowired
    CourseRepository repository;
    @Override
    public Course save(Course entity) {
        return repository.save(entity);
    }

    @Override
    public Course findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public List<Course> findAllCourseByHocKyAndKhoa(String hocKy, String khoa) {
        return repository.findAllCourseByHockyAndDepartment(hocKy, khoa);

    public Course getCourseOfClazz(Long id) {
        return repository.getCourseOfClazz(id);

    }
}
