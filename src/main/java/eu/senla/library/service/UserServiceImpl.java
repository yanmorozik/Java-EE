package eu.senla.library.service;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.UserService;
import eu.senla.library.dto.UserDto;
import eu.senla.library.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User response = userRepository.add(user);
        return modelMapper.map(response, UserDto.class);
    }

    @Transactional
    @Override
    public UserDto getById(Long id) {
        User response = userRepository.findById(id);
        return modelMapper.map(response, UserDto.class);
    }

    @Transactional
    @Override
    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User response = userRepository.update(user);
        return modelMapper.map(response, UserDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
