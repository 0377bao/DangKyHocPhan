package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Administrator;
import iuh.dangkyhocphan.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService implements IService<Administrator, Long>{
    @Autowired
    AdministratorRepository repository;
    @Override
    public Administrator save(Administrator entity) {
        return repository.save(entity);
    }

    @Override
    public Administrator findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Administrator> findAll() {
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
