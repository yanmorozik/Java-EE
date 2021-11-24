package eu.senla.library.repository;

import eu.senla.library.api.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractRepositoryImpl<T> implements AbstractRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> entityClass;

    public AbstractRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T add(T entity) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> entityCriteria = cb.createQuery(entityClass);
        Root<T> personRoot = entityCriteria.from(entityClass);
        entityCriteria.select(personRoot);
        entityManager.createQuery(entityCriteria)
                .getResultList();
        //
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public T findById(Long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("from " + entityClass.getName(), entityClass);
        return entityManager.find(entityClass, id);
    }

    @Override   //не уверен
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteById(Long id) {
        T removeEntity = entityManager.find(entityClass, id);
        entityManager.remove(removeEntity);
    }
}
