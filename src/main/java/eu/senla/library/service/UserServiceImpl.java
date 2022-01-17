package eu.senla.library.service;

import eu.senla.library.api.repository.CredentialRepository;
import eu.senla.library.api.repository.RoleRepository;
import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.CredentialConverter;
import eu.senla.library.converter.RoleConverter;
import eu.senla.library.converter.UserConverter;
import eu.senla.library.converter.UserConverterWithBookWithRelationIdsDto;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.dto.UserDto;
import eu.senla.library.dto.UserWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final UserConverter userConverter;
    private final CredentialConverter credentialConverter;
    private final UserConverterWithBookWithRelationIdsDto userWithRelationConverter;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDto getById(Long id) throws NotFoundException {
        User response = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return userConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAll(int start,int max) {
        List<User> users = userRepository.findAll(start,max);
        return userConverter.convert(users);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userConverter.convert(users);
    }

    @Transactional
    @Override
    public UserDto update(UserWithRelationIdsDto userWithRelationIdsDto) {
        final User response = userRepository.update(reassignment(userWithRelationIdsDto));
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

        credentialRepository.add(credential);

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        final User user = userConverter.convert(userDto);
        user.setRoles(roles);
        user.setCredential(credential);
        userRepository.add(user);

        return userConverter.convert(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findByUsername(String name) {
        return userConverter.convert(userRepository.findByUsername(name));
    }

    public User reassignment(UserWithRelationIdsDto userWithRelationIdsDto) {
        final User user = userWithRelationConverter.convert(userWithRelationIdsDto);

        Set<Role> roles = userWithRelationIdsDto.getRoleIds()
                .stream()
                .map(id -> roleRepository.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        Credential credential = credentialConverter.convert(userWithRelationIdsDto.getCredential());
        credential.setLogin(userWithRelationIdsDto.getCredential().getLogin());
        credential.setPassword(passwordEncoder.encode(userWithRelationIdsDto.getCredential().getPassword()));
        user.setCredential(credential);
        return user;
    }

    @Transactional
    @Override
    public List<UserDto> getByFiler(String firstName, String surname,String telephone) {

        UserDto filter = UserDto.builder().firstName(firstName).surname(surname).telephone(telephone).build();
        List<User> users = userRepository.findAll();
        List<UserDto> userProtocols = userConverter.convert(users);
        List<Function<UserDto, String>> comparingFields = Arrays.asList(UserDto::getFirstName,
                UserDto::getSurname,UserDto::getTelephone);
        return filter(userProtocols, filter, comparingFields);

    }

    private List<UserDto> filter(List<UserDto> allProtocols, UserDto filter,
                                   List<Function<UserDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private boolean test(UserDto protocol, UserDto filter,
                         List<Function<UserDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }
}
