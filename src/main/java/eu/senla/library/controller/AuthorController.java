package eu.senla.library.controller;

import eu.senla.library.api.service.AuthorService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
        AuthorDto dto = authorService.create(authorDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AuthorDto> getById(@PathVariable Long id) throws NotFoundException {
        AuthorDto dto = authorService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<AuthorDto>> getAll(@RequestParam(defaultValue = "1") int start,
                                                  @RequestParam(defaultValue = "3") int max) {
        List<AuthorDto> authors = authorService.getAll(start, max);
        return ResponseEntity.ok(authors);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto authorDto) {
        AuthorDto dto = authorService.update(authorDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<AuthorDto>> getByFiler(@RequestParam(defaultValue = "") String firstName,
                                                      @RequestParam(defaultValue = "") String surname) {
        List<AuthorDto> authors = authorService.getByFiler(firstName, surname);
        return ResponseEntity.ok(authors);
    }
}
