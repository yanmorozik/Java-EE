package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.UserService;
import eu.senla.library.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ObjectMapper mapper;

    UserDto userDto;

    public String create(String requestJson) {
        try {
            userDto = mapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.create(userDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            userDto = userService.getById(id);
            return mapper.writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<UserDto> users = userService.getAll();
            return mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            userDto = mapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.update(userDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(UserDto userDto) {
        userService.delete(userDto);
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }
}
