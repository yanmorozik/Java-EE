package eu.senla.library.api.repository;

import eu.senla.library.model.Role;

import java.util.List;

public interface RoleRepository {

    Role add(Role role);

    Role findById(Long id);

    List<Role> findAll();

    Role update(Role role);

    void delete(Role role);

    void deleteById(Long id);
}
