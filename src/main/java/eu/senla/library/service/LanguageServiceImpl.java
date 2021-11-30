package eu.senla.library.service;

import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.api.service.LanguageService;
import eu.senla.library.converter.LanguageConverter;
import eu.senla.library.dto.LanguageDto;
import eu.senla.library.exception.LanguageNotFoundException;
import eu.senla.library.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageConverter languageConverter;

    @Transactional
    @Override
    public LanguageDto create(LanguageDto languageDto) {
        final Language language = languageConverter.convert(languageDto);
        final Language response = languageRepository.add(language);
        return languageConverter.convert(response);
    }

    @Transactional
    @Override
    public LanguageDto getById(Long id) throws LanguageNotFoundException {
        Language response = Optional.ofNullable(languageRepository.findById(id)).orElseThrow(() -> new LanguageNotFoundException(id));
        return languageConverter.convert(response);
    }

    @Transactional
    @Override
    public List<LanguageDto> getAll() {
        List<Language> languages = languageRepository.findAll();
        return languageConverter.convert(languages);
    }

    @Transactional
    @Override
    public LanguageDto update(LanguageDto languageDto) {
        final Language language = languageConverter.convert(languageDto);
        final Language response = languageRepository.update(language);
        return languageConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
