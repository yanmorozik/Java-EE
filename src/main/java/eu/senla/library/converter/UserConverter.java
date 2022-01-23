package eu.senla.library.converter;

import eu.senla.library.dto.UserDto;
import eu.senla.library.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends Converter<User, UserDto> {
    public UserConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
