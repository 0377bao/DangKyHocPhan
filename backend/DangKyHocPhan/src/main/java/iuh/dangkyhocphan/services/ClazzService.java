package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Clazz;
import iuh.dangkyhocphan.respositories.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService implements IService<Clazz, Long>{
    @Autowired
    private ClazzRepository repository;
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
