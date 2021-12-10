package eu.senla.library.service;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.api.service.RoleService;
import eu.senla.library.converter.RoleConverter;
import eu.senla.library.dto.RoleDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Transactional
    @Override
    public RoleDto create(RoleDto roleDto) {
        final Role role = roleConverter.convert(roleDto);
        final Role response = roleRepository.add(role);
        return roleConverter.convert(response);
    }

    @Transactional
    @Override
    public RoleDto getById(Long id) throws NotFoundException {
        Role response = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return roleConverter.convert(response);
    }

    @Transactional
    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roleConverter.convert(roles);
    }

    @Transactional
    @Override
    public RoleDto update(RoleDto roleDto) {
        final Role role = roleConverter.convert(roleDto);
        final Role response = roleRepository.update(role);
        return roleConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
