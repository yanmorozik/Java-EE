package eu.senla.library.repository;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.model.Role;
import eu.senla.library.model.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role findByNameRole(String name) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        final Root<Role> root = query.from(Role.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get(Role_.nameRole), name));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    protected String getNameGraph() {
        return "with-users";
    }
}
