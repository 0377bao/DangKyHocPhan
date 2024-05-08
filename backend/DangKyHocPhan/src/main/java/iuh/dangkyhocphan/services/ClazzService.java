package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Clazz;
import iuh.dangkyhocphan.models.Schedule;
import iuh.dangkyhocphan.repositories.ClazzRepository;
import iuh.dangkyhocphan.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService implements IService<Clazz, Long> {
    @Autowired
    private ClazzRepository repository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public Clazz save(Clazz entity) {
        return repository.save(entity);
    }

    @Override
    public Clazz findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Clazz> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Clazz can't be deleted!");
        }
    }

    @Override
    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Clazzs can't be deleted!");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public List<Schedule> findScheduleOfClazz(Long clazzId) {
        return scheduleRepository.findAllScheduleOfCourseByCourseId(clazzId);
    }
}
