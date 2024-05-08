package iuh.dangkyhocphan.services;

import java.util.List;

public interface IService<T, Long>{
    public T save(T entity);
    public T findById(Long id);
    public List<T> findAll();
    public void deleteById(Long id);
    public void deleteAll();
    public boolean existsById(Long id);
}
