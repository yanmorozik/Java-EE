package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.RoleService;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.dto.RoleDto;
import eu.senla.library.exception.PublisherNotFoundException;
import eu.senla.library.exception.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(
            RoleController.class);

    private final RoleService roleService;

    @PostMapping
    public RoleDto create(@RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) throws RoleNotFoundException {
        return roleService.getById(id);
    }

    @GetMapping
    public List<RoleDto> getAll() {
        return roleService.getAll();
    }

    @PutMapping
    public RoleDto update(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }

}
