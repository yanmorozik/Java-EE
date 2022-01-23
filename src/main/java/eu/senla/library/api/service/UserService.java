package eu.senla.library.api.service;

import eu.senla.library.dto.UserDto;
import eu.senla.library.dto.UserWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface UserService {

    UserDto getById(Long id) throws NotFoundException;

    List<UserDto> getAll(int start, int max);

    UserDto update(UserWithRelationIdsDto userWithRelationIdsDto);

    void deleteById(Long id);

    UserDto register(UserDto user);

    UserDto findByUsername(String name);

    List<UserDto> getByFiler(String firstName, String surname, String telephone, int start, int max);

}
