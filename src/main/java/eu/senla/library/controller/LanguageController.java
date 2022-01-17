package eu.senla.library.controller;

import eu.senla.library.api.service.LanguageService;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.dto.LanguageDto;
import eu.senla.library.exception.NotFoundException;
import liquibase.pro.packaged.V;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("languages")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LanguageDto> create(@RequestBody LanguageDto languageDto) {
        LanguageDto dto = languageService.create(languageDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LanguageDto> getById(@PathVariable Long id) throws NotFoundException {
        LanguageDto dto = languageService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<LanguageDto>> getAll(@RequestParam(defaultValue = "1") int start,
                                                    @RequestParam(defaultValue = "3") int max) {
        List<LanguageDto> languages=languageService.getAll(start,max);
        return ResponseEntity.ok(languages);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<LanguageDto>> getAll() {
        List<LanguageDto> languages=languageService.getAll();
        return ResponseEntity.ok(languages);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LanguageDto> update(@RequestBody LanguageDto languageDto) {
        LanguageDto dto = languageService.update(languageDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        languageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<LanguageDto>> getByFiler(@RequestParam(defaultValue = "") String nameLanguage) {
        List<LanguageDto> languages = languageService.getByFiler(nameLanguage);
        return ResponseEntity.ok(languages);
    }
}
