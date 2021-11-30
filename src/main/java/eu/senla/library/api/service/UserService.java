package eu.senla.library.api.service;

import eu.senla.library.dto.UserDto;
import eu.senla.library.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto getById(Long id) throws UserNotFoundException;

    List<UserDto> getAll();

    UserDto update(UserDto userDto);

    void deleteById(Long id);
}
