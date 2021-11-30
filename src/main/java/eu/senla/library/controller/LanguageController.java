package eu.senla.library.controller;

import eu.senla.library.api.service.LanguageService;
import eu.senla.library.dto.LanguageDto;
import eu.senla.library.exception.LanguageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("languages")
public class LanguageController {

    private static final Logger logger = LoggerFactory.getLogger(
            LanguageController.class);

    private final LanguageService languageService;

    @PostMapping
    public LanguageDto create(@RequestBody LanguageDto languageDto) {
        return languageService.create(languageDto);
    }

    @GetMapping("/{id}")
    public LanguageDto getById(@PathVariable Long id) throws LanguageNotFoundException {
        return languageService.getById(id);
    }

    @GetMapping
    public List<LanguageDto> getAll() {
        return languageService.getAll();
    }

    @PutMapping
    public LanguageDto update(@RequestBody LanguageDto languageDto) {
        return languageService.update(languageDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        languageService.deleteById(id);
    }
}
