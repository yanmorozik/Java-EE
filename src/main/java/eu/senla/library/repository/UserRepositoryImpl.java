package eu.senla.library.repository;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.model.Credential;
import eu.senla.library.model.Credential_;
import eu.senla.library.model.User;
import eu.senla.library.model.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Credential> credentialCriteriaQuery = builder.createQuery(Credential.class);
        final Root<Credential> rootCred = credentialCriteriaQuery.from(Credential.class);
        credentialCriteriaQuery.select(rootCred).where(builder.equal(rootCred.get(Credential_.login), name));
        Credential credential = entityManager.createQuery(credentialCriteriaQuery).getSingleResult();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);


        root.fetch(User_.credential, JoinType.INNER);
        query.select(root).where(builder.equal(root.get(User_.credential), credential));

        return entityManager.createQuery(query).getSingleResult();

//        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        final CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        final Root<User> root = query.from(User.class);
//        final Root<Credential> rootCred = query.from(Credential.class);
//
//        root.fetch(User_.credential, JoinType.INNER);
//        query.select(root).where(criteriaBuilder.equal(rootCred.get(Credential_.login), name));
//        return entityManager.createQuery(query).getResultList().stream().findFirst().orElse(null);
    }


    @Override
    protected String getNameGraph() {
        return "userEntityGraph";
    }
}
