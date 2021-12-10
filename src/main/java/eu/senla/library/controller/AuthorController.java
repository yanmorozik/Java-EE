package eu.senla.library.controller;

import eu.senla.library.api.service.AuthorService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
        AuthorDto dto = authorService.create(authorDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable Long id) throws NotFoundException {
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
