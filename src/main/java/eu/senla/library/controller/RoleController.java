package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.RoleService;
import eu.senla.library.dto.RoleDto;
import eu.senla.library.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor()
public class RoleController {

    private final RoleService roleService;

    private final ObjectMapper mapper;

    public String create(String requestJson) {
        try {
            RoleDto roleDto = mapper.readValue(requestJson, RoleDto.class);
            RoleDto response = roleService.create(roleDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            RoleDto roleDto = roleService.getById(id);
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
            RoleDto roleDto = mapper.readValue(requestJson, RoleDto.class);
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

    public String getUserRoleWithUserJPQL(Long id) {
        try {
            RoleDto roleDto = roleService.getUserRoleWithUserJPQL(id);
            return mapper.writeValueAsString(roleDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getUserRoleWithUserCriteria(Long id) {
        try {
            RoleDto roleDto = roleService.getUserRoleWithUserCriteria(id);
            return mapper.writeValueAsString(roleDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getUserRoleWithUserGraph(Long id) {
        try {
            RoleDto roleDto = roleService.getUserRoleWithUserGraph(id);
            return mapper.writeValueAsString(roleDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
