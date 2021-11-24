package eu.senla.library.repository;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

//    @PersistenceContext
//    private EntityManager entityManager;

    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role getByIdWithUsers(Long id) {
        return null;
    }

//    @Override
//    public Role getByIdWithUsers(Long id) {
//        return entityManager.createQuery("select role from Role role left join role.users where role.id =:id", Role.class)
//                .setParameter("id", id).getSingleResult();
//    }
}
