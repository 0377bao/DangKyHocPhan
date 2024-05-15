package iuh.dangkyhocphan.repositories;

import iuh.dangkyhocphan.models.Account;
import iuh.dangkyhocphan.models.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select acc from Account acc where acc.user.id = :username and acc.matKhau = :password")
    Account findAccountByUsernameAndPassword(Long username, String password);
}
