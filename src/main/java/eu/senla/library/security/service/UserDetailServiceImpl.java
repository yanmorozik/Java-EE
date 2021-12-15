package eu.senla.library.security.service;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.CredentialService;
import eu.senla.library.model.Credential;
import eu.senla.library.model.Role;
import eu.senla.library.repository.CredentialRepositoryImpl;
import eu.senla.library.service.CredentialServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = Optional.ofNullable(userRepository.getByNameWithRoles(username))
               .orElseThrow(()-> new UsernameNotFoundException(username + "not found"));

       return new User(user.getFirstName(),
               user.getCredential().getPassword(),
               Optional.ofNullable(user.getRoles()).map(Role::getNameRole).map(
                       x-> List.of(
                               new SimpleGrantedAuthority(
                                       "ROLE_"+ x
                               )
                       )
               ).orElse(Collections.emptyList())
       );
    }
}
