package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.RoleService;
import eu.senla.library.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor()
public class RoleController {

    private final RoleService roleService;

    private final ObjectMapper mapper;

    RoleDto roleDto;

    public String create(String requestJson) {
        try {
            roleDto = mapper.readValue(requestJson, RoleDto.class);
            RoleDto response = roleService.create(roleDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            roleDto = roleService.getById(id);
            return mapper.writeValueAsString(roleDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<RoleDto> roles = roleService.getAll();
            return mapper.writeValueAsString(roles);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            roleDto = mapper.readValue(requestJson, RoleDto.class);
            RoleDto response = roleService.update(roleDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        roleService.deleteById(id);
    }
}
