package eu.senla.library.api.service;

import eu.senla.library.dto.LanguageDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface LanguageService {

    LanguageDto create(LanguageDto languageDto);

    LanguageDto getById(Long id) throws NotFoundException;

    List<LanguageDto> getAll();

    LanguageDto update(LanguageDto languageDto);

    void deleteById(Long id);
}
