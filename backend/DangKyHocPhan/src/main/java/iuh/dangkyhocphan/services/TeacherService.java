package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Teacher;
import iuh.dangkyhocphan.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements IService<Teacher, Long>{
    @Autowired
    private TeacherRepository repository;
    @Override
    public Teacher save(Teacher entity) {
        return repository.save(entity);
    }

    @Override
    public Teacher findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Teacher> findAll() {
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
}
