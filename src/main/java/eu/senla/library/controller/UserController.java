package eu.senla.library.controller;

import eu.senla.library.api.service.UserService;
import eu.senla.library.dto.UserDto;
import eu.senla.library.dto.UserWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserDto> create(@RequestBody UserWithRelationIdsDto userWithRelationIdsDto) {
//        UserDto dto = userService.create(userWithRelationIdsDto);
//        return ResponseEntity.ok(dto);
//    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) throws NotFoundException {
        UserDto dto = userService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> update(@RequestBody UserWithRelationIdsDto userWithRelationIdsDto) {
        UserDto dto = userService.update(userWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
