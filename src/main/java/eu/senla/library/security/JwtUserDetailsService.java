package eu.senla.library.security;

import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.UserConverter;
import eu.senla.library.model.User;
import eu.senla.library.security.jwt.JwtUserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userConverter.convert(userService.findByUsername(username));

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return JwtUserFactory.create(user);
    }
}
