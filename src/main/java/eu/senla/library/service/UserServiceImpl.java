package eu.senla.library.service;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.UserConverter;
import eu.senla.library.dto.UserDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        final User user = userConverter.convert(userDto);
        final User response = userRepository.add(user);
        return userConverter.convert(response);
    }

    @Transactional
    @Override
    public UserDto getById(Long id) throws NotFoundException {
        User response = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return userConverter.convert(response);
    }

    @Transactional
    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userConverter.convert(users);
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        final User user = userConverter.convert(userDto);
        final User response = userRepository.update(user);
        return userConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
