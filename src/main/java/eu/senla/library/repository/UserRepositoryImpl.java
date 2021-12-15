package eu.senla.library.repository;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.model.Author;
import eu.senla.library.model.Author_;
import eu.senla.library.model.User;
import eu.senla.library.model.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User getByNameWithRoles(String name) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        final Root<User> rows = query.from(User.class);
        query.where(criteriaBuilder.equal(rows.get(User_.FIRST_NAME), name));
        return entityManager.createQuery(query).getSingleResult();
    }
}
