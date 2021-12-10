package eu.senla.library.converter;

import eu.senla.library.dto.RoleDto;
import eu.senla.library.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends Converter<Role,RoleDto>{
    public RoleConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
