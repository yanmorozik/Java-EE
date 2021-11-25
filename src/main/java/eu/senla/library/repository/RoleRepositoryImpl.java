package eu.senla.library.repository;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.model.Role;
import eu.senla.library.model.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role getByIdWithUsersJPQL(Long id) {
        return entityManager.createQuery("select role from Role role left join fetch role.users where role.id =:id", Role.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Role getByIdWithUsersCriteria(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        final Root<Role> from = query.from(Role.class);
        from.fetch(Role_.users, JoinType.INNER);

        return entityManager.createQuery(
                query.select(from).where(criteriaBuilder.equal(from.get(Role_.id), id))).getSingleResult();
    }

    @Override
    public Role getByIdWithUsersGraph(Long id) {
        EntityGraph<?> graph = this.entityManager.getEntityGraph("with-users");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(Role.class, id, hints);
    }

}
