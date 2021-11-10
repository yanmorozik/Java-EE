package eu.senla.library.api.service;

import eu.senla.library.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto getById(Long id);

    List<UserDto> getAll();

    UserDto update(UserDto userDto);

    void delete(UserDto userDto);

    void deleteById(Long id);
}
