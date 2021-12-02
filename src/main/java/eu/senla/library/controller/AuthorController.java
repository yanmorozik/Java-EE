package eu.senla.library.controller;

import eu.senla.library.api.service.AuthorService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(
            AuthorController.class);

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
        AuthorDto dto = authorService.create(authorDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable Long id) throws AuthorNotFoundException {
        AuthorDto dto = authorService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @PutMapping
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto authorDto){
        AuthorDto dto = authorService.update(authorDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
