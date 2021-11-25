package eu.senla.library.api.repository;

import eu.senla.library.model.Role;

import java.util.List;

public interface RoleRepository extends AbstractRepository<Role>{

    List<Role> getByIdWithUsersJPQL(Long id);

    Role getByIdWithUsersCriteria(Long id);

    Role getByIdWithUsersGraph(Long id);
}
