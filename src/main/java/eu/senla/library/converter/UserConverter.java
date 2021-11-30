package eu.senla.library.converter;

import eu.senla.library.dto.UserDto;
import eu.senla.library.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final ModelMapper modelMapper;

    public User convert(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto convert(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convert(List<User> users) {
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }
}
