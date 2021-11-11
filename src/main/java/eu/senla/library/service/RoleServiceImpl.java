package eu.senla.library.service;

import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.api.service.RoleService;
import eu.senla.library.dto.RoleDto;
import eu.senla.library.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role response = roleRepository.add(role);
        return modelMapper.map(response, RoleDto.class);
    }

    @Override
    public RoleDto getById(Long id) {
        Role response = roleRepository.findById(id);
        return modelMapper.map(response, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {

        List<Role> roles = roleRepository.findAll();
        return modelMapper.map(roles, new TypeToken<List<RoleDto>>() {
        }.getType());
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role response = roleRepository.update(role);
        return modelMapper.map(response, RoleDto.class);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
