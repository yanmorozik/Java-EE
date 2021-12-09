package eu.senla.library.api.service;

import eu.senla.library.dto.RoleDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface RoleService {

    RoleDto create(RoleDto roleDto);

    RoleDto getById(Long id) throws NotFoundException;

    List<RoleDto> getAll();

    RoleDto update(RoleDto roleDto);

    void deleteById(Long id);

}
