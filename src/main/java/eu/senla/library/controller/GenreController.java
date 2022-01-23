package eu.senla.library.controller;

import eu.senla.library.api.service.GenreService;
import eu.senla.library.dto.GenreDto;
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
@RequestMapping("genres")
public class GenreController{

    private final GenreService genreService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDto create(@RequestBody GenreDto genreDto) {
        return genreService.create(genreDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDto getById(@PathVariable Long id) throws NotFoundException {
        return genreService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<GenreDto> getAll(@RequestParam(defaultValue = "1") int start,
                                                 @RequestParam(defaultValue = "3") int max) {
        return genreService.getAll(start,max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDto update(@RequestBody GenreDto genreDto) {
        return genreService.update(genreDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<GenreDto> getByFiler(@RequestParam(defaultValue = "") String nameGenre,
                                                     @RequestParam(defaultValue = "1") int start,
                                                     @RequestParam(defaultValue = "3") int max) {
        return genreService.getByFiler(nameGenre,start,max);
    }

}
