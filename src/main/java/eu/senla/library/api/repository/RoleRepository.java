package eu.senla.library.api.repository;

import eu.senla.library.model.Role;

public interface RoleRepository extends AbstractRepository<Role>{

    Role getByIdWithUsersJPQL(Long id);

    Role getByIdWithUsersCriteria(Long id);

    Role getByIdWithUsersGraph(Long id);
}
