package eu.senla.library.controller;

import eu.senla.library.api.service.RoleService;
import eu.senla.library.dto.ErrorMessageDto;
import eu.senla.library.dto.RoleDto;
import eu.senla.library.exception.BookNotFoundException;
import eu.senla.library.exception.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(
            RoleController.class);

    private final RoleService roleService;

    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorMessageDto catchException(){
        return new ErrorMessageDto("error");
    }

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
