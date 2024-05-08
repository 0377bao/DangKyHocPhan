package iuh.dangkyhocphan.respositories;

import iuh.dangkyhocphan.models.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
}
