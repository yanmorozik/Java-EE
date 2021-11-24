package eu.senla.library.api.repository;

import eu.senla.library.dto.RoleDto;
import eu.senla.library.model.Book;
import eu.senla.library.model.Role;

import java.util.List;

public interface RoleRepository extends AbstractRepository<Role>{

    Role add(Role role);

    Role findById(Long id);

    List<Role> findAll();

    Role update(Role role);

    void delete(Role role);

    void deleteById(Long id);

    Role getByIdWithUsers(Long id);
}
