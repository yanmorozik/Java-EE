package eu.senla.library.controller;

import eu.senla.library.api.service.GenreService;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("genres")
public class GenreController{

    private final GenreService genreService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GenreDto> create(@RequestBody GenreDto genreDto) {
        GenreDto dto = genreService.create(genreDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GenreDto> getById(@PathVariable Long id) throws NotFoundException {
        GenreDto dto = genreService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<GenreDto>> getAll() {
        List<GenreDto> genres = genreService.getAll();
        return ResponseEntity.ok(genres);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GenreDto> update(@RequestBody GenreDto genreDto) {
        GenreDto dto = genreService.update(genreDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
