package eu.senla.library.api.repository;

import java.util.List;

public interface AbstractRepository<T> {

    T add(T entity);

    T findById(Long id);

    List<T> findAll();

    T update(T entity);

    void deleteById(Long id);
}
