package eu.senla.library.repository;

import eu.senla.library.api.repository.CredentialRepository;
import eu.senla.library.model.Credential;
import eu.senla.library.model.Credential_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Repository
public class CredentialRepositoryImpl extends AbstractRepositoryImpl<Credential> implements CredentialRepository {

    public CredentialRepositoryImpl() {
        super(Credential.class);
    }

    @Override
    public Credential findByLogin(String login){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Credential> query = builder.createQuery(Credential.class);
        Root<Credential> root = query.from(Credential.class);

        root.fetch(Credential_.user, JoinType.INNER);
        query.select(root);
        query.where(builder.equal(root.get(Credential_.login), login));
        return entityManager.createQuery(query).getSingleResult();
    }
}
