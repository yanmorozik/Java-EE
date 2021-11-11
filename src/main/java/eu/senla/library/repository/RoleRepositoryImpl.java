package eu.senla.library.repository;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.model.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final List<Role> roles = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }

    @Override
    public Role add(Role role) {
        role.setId(generateFacilitiesId());
        roles.add(role);
        return role;
    }

    @Override
    public Role findById(Long id) {
        return roles.get(id.intValue());
    }

    @Override
    public List<Role> findAll() {
        return roles;
    }

    @Override
    public Role update(Role role) {
        Long index = role.getId();
        roles.set(index.intValue(),role);
        return role;
    }

    @Override
    public void delete(Role role) {
        roles.remove(role);
    }

    @Override
    public void deleteById(Long id) {
        roles.remove(id.intValue());
    }
}
