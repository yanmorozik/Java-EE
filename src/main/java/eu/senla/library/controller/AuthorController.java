package eu.senla.library.controller;

import eu.senla.library.api.service.AuthorService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorDto create(@RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorDto getById(@PathVariable Long id) throws NotFoundException {
        return authorService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AuthorDto> getAll(@RequestParam(defaultValue = "1") int start,
                                  @RequestParam(defaultValue = "3") int max) {
        return authorService.getAll(start, max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorDto update(@RequestBody AuthorDto authorDto) {
        return authorService.update(authorDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AuthorDto> getByFiler(@RequestParam(defaultValue = "") String firstName,
                                      @RequestParam(defaultValue = "") String surname,
                                      @RequestParam(defaultValue = "1") int start,
                                      @RequestParam(defaultValue = "3") int max) {
        return authorService.getByFiler(firstName, surname, start, max);
    }
}

//  Post запрос dto
//контроллер принимает credential
//возвращает token
