package eu.senla.library.api.service;

import eu.senla.library.dto.LanguageDto;

import java.util.List;

public interface LanguageService {

    LanguageDto create(LanguageDto languageDto);

    LanguageDto getById(Long id);

    List<LanguageDto> getAll();

    LanguageDto update(LanguageDto languageDto);

    void delete(LanguageDto LanguageDto);

    void deleteById(Long id);
}
