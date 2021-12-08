package eu.senla.library.repository;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

    public RoleRepositoryImpl() {
        super(Role.class);
    }
}
