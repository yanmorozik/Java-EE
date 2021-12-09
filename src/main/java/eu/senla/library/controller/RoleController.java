package eu.senla.library.controller;

import eu.senla.library.api.service.RoleService;
import eu.senla.library.dto.RoleDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public RoleDto create(@RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) throws NotFoundException {
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
