package eu.senla.library.controller;

import eu.senla.library.api.service.LanguageService;
import eu.senla.library.dto.LanguageDto;
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
@RequestMapping("languages")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LanguageDto create(@RequestBody LanguageDto languageDto) {
        return languageService.create(languageDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LanguageDto getById(@PathVariable Long id) throws NotFoundException {
        return languageService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<LanguageDto> getAll(@RequestParam(defaultValue = "1") int start,
                                                    @RequestParam(defaultValue = "3") int max) {
        return languageService.getAll(start,max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LanguageDto update(@RequestBody LanguageDto languageDto) {
        return languageService.update(languageDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        languageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<LanguageDto> getByFiler(@RequestParam(defaultValue = "") String nameLanguage,
                                                        @RequestParam(defaultValue = "1") int start,
                                                        @RequestParam(defaultValue = "3") int max) {
        return languageService.getByFiler(nameLanguage,start,max);
    }
}
