package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Course;
import iuh.dangkyhocphan.models.CourseOpening;
import iuh.dangkyhocphan.repositories.CourseOpeningRepository;
import iuh.dangkyhocphan.repositories.CourseRepository;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseOpeningService implements IService<CourseOpening, Long> {
    @Autowired
    private CourseOpeningRepository repository;

    @Override
    public CourseOpening save(CourseOpening entity) {
        return repository.save(entity);
    }

    @Override
    public CourseOpening findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CourseOpening> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("CourseOpening can't be deleted!");
        }

    }

    @Override
    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("CourseOpenings can't be deleted!");
        }

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

}
