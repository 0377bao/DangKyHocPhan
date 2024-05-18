package iuh.dangkyhocphan.services;

import iuh.dangkyhocphan.models.Account;
import iuh.dangkyhocphan.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IService<Account, Long>{
    @Autowired
    private AccountRepository repository;
    @Override
    public Account save(Account entity) {
        return repository.save(entity);
    }

    @Override
    public Account findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Account findAccountToLogin(Long username, String password) {
        return repository.findAccountByUsernameAndPassword(username, password);
    }
}
