package eu.senla.library.api.service;

import eu.senla.library.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto create(RoleDto roleDto);

    RoleDto getById(Long id);

    List<RoleDto> getAll();

    RoleDto update(RoleDto roleDto);

    void deleteById(Long id);

    RoleDto getUserRoleWithUser(Long id);
}
