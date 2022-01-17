package eu.senla.library.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T> {

    T add(T entity);

    Optional<T> findById(Long id);

    List<T> findAll(int start, int max);

    List<T> findAll();

    T update(T entity);

    void deleteById(Long id);

}
