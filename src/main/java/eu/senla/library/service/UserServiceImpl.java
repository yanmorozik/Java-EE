package eu.senla.library.service;

import eu.senla.library.api.repository.CredentialRepository;
import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.CredentialConverter;
import eu.senla.library.converter.RoleConverter;
import eu.senla.library.converter.UserConverter;
import eu.senla.library.dto.UserDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Credential;
import eu.senla.library.model.Role;
import eu.senla.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;

    private final UserConverter userConverter;
    private final RoleConverter roleConverter;
    private final CredentialConverter credentialConverter;

    private final BCryptPasswordEncoder passwordEncoder;

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

    @Transactional
    @Override
    public UserDto register(UserDto userDto) {

        Role roleUser = roleRepository.findByNameRole("ROLE_USER");

        Credential credential = credentialConverter.convert(userDto.getCredential());
        credential.setPassword(passwordEncoder.encode(userDto.getCredential().getPassword()));
        credential.setPasswordConfirm(passwordEncoder.encode(userDto.getCredential().getPasswordConfirm()));

        credentialRepository.add(credential);

        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        final User user = userConverter.convert(userDto);
        user.setRoles(roles);
        user.setCredential(credential);
        userRepository.add(user);

        return userConverter.convert(user);
    }

    @Transactional
    @Override
    public UserDto findByUsername(String name) {
        return userConverter.convert(userRepository.findByUsername(name));
    }
}
