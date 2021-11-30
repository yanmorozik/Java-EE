package eu.senla.library.converter;

import eu.senla.library.dto.RoleDto;
import eu.senla.library.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleConverter {
    private final ModelMapper modelMapper;

    public Role convert(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    public RoleDto convert(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    public List<RoleDto> convert(List<Role> roles) {
        return modelMapper.map(roles, new TypeToken<List<RoleDto>>() {
        }.getType());
    }
}
