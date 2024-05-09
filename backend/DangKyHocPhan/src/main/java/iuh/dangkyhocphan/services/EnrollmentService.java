package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Enrollment;
import iuh.dangkyhocphan.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService implements IService<Enrollment, Long>{
    @Autowired
    private EnrollmentRepository repository;
    @Override
    public Enrollment save(Enrollment entity) {
        return repository.save(entity);
    }

    @Override
    public Enrollment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Enrollment> findAll() {
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


    public List<Enrollment> findAllEnrollmentByStudentId(Long studentId) {
        return repository.findEnrollmentOfStudent(studentId);
    }

    public List<Enrollment> findEnrollmentOfStudent(Long id) {
        return repository.findEnrollmentOfStudent(id);
    }
    public List<String> findSemesterOfStudent(Long id) {
        return repository.findSemesterOfStudent(id);

    }
}
