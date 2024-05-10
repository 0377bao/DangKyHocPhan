package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Schedule;
import iuh.dangkyhocphan.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService implements IService<Schedule, Long>{
    @Autowired
    private ScheduleRepository repository;
    @Override
    public Schedule save(Schedule entity) {
        return repository.save(entity);
    }

    @Override
    public Schedule findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Schedule> findAll() {
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

    public List<Schedule> findScheduleByClass(Long clazzId) {
        return repository.findScheduleByClazz(clazzId);
    }

    public List<Schedule> findScheduleOfStudent(Long id, LocalDate tuNgay, LocalDate denNgay) {
        return repository.findScheduleOfStudent(id, tuNgay, denNgay);
    }

    public List<Schedule> findScheduleOfStudentBySemester(Long id, String hocKi) {
        return repository.findScheduleOfStudentBySemester(id, hocKi);
    }
}
