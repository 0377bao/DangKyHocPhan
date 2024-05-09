package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Student;
import iuh.dangkyhocphan.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IService<Student, Long>{
    @Autowired
    private StudentRepository repository;
    @Override
    public Student save(Student entity) {
        return null;
    }

    @Override
    public Student findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
