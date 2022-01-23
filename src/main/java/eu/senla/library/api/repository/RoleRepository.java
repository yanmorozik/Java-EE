package eu.senla.library.api.repository;

import eu.senla.library.model.Role;

public interface RoleRepository extends AbstractRepository<Role>{
    Role findByNameRole(String name);
}
