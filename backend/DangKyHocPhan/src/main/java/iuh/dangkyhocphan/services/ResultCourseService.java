package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.ResultCourse;
import iuh.dangkyhocphan.repositories.ResultCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultCourseService implements IService<ResultCourse, Long>{
    @Autowired
    ResultCourseRepository repository;
    @Override
    public ResultCourse save(ResultCourse entity) {
        return repository.save(entity);
    }

    @Override
    public ResultCourse findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ResultCourse> findAll() {
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

    public ResultCourse getResultCourseOfStudent(Long id, Long courseId) {
        return repository.getResultCourseOfStudent(id, courseId);
    }
}
