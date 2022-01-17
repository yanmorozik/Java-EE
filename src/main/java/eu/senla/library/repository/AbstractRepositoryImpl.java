package eu.senla.library.repository;

import eu.senla.library.api.repository.AbstractRepository;
import eu.senla.library.model.Book;
import eu.senla.library.model.Genre;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepositoryImpl<T> implements AbstractRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> entityClass;

    public AbstractRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T add(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.of(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll(int start, int max) {
        EntityGraph entityGraph = entityManager.getEntityGraph(getNameGraph());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .setFirstResult(start-1)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public List<T> findAll() {
        EntityGraph entityGraph = entityManager.getEntityGraph(getNameGraph());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        T removeEntity = entityManager.find(entityClass, id);
        entityManager.remove(removeEntity);
    }

    protected abstract String getNameGraph();
}
