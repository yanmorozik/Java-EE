package eu.senla.library.controller;

import eu.senla.library.api.service.UserService;
import eu.senla.library.dto.UserDto;
import eu.senla.library.dto.UserWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDto getById(@PathVariable Long id) throws NotFoundException {
        return userService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<UserDto> getAll(@RequestParam(defaultValue = "1") int start,
                                                @RequestParam(defaultValue = "3") int max) {
        return userService.getAll(start, max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDto update(@RequestBody UserWithRelationIdsDto userWithRelationIdsDto) {
        return userService.update(userWithRelationIdsDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<UserDto> getByFiler(@RequestParam(defaultValue = "") String firstName,
                                                    @RequestParam(defaultValue = "") String surname,
                                                    @RequestParam(defaultValue = "") String telephone,
                                                    @RequestParam(defaultValue = "1") int start,
                                                    @RequestParam(defaultValue = "3") int max) {
        return userService.getByFiler(firstName, surname, telephone, start, max);
    }
}
