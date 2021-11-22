package eu.senla.library.repository;

import eu.senla.library.api.repository.AbstractRepository;

import java.util.List;

public class AbstractRepositoryImpl<T> implements AbstractRepository<T> {
    @Override
    public T add(T entity) {
        return null;
    }

    @Override
    public T findById(Long id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
