package eu.senla.library.repository;

import eu.senla.library.api.repository.CredentialRepository;
import eu.senla.library.model.Credential;
import eu.senla.library.model.Credential_;
import eu.senla.library.model.Role;
import eu.senla.library.model.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CredentialRepositoryImpl extends AbstractRepositoryImpl<Credential> implements CredentialRepository {
    public CredentialRepositoryImpl() {
        super(Credential.class);
    }

    @Override
    protected String getNameGraph() {
        return "credentialEntityGraph";
    }

    @Override
    public Credential findByLogin(String login) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Credential> query = criteriaBuilder.createQuery(Credential.class);
        final Root<Credential> root = query.from(Credential.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get(Credential_.login), login));
        return entityManager.createQuery(query).getSingleResult();
    }
}
