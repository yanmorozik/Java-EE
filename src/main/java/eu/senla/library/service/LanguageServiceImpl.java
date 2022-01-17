package eu.senla.library.service;

import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.api.service.LanguageService;
import eu.senla.library.converter.LanguageConverter;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.dto.LanguageDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Genre;
import eu.senla.library.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    @Override
    public LanguageDto getById(Long id) throws NotFoundException {
        Language response = languageRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return languageConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LanguageDto> getAll(int start,int max) {
        List<Language> languages = languageRepository.findAll(start,max);
        return languageConverter.convert(languages);
    }

    @Transactional(readOnly = true)
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

    @Transactional
    @Override
    public List<LanguageDto> getByFiler(String nameLanguage) {

        LanguageDto filter = LanguageDto.builder().nameLanguage(nameLanguage).build();
        List<Language> languages = languageRepository.findAll();
        List<LanguageDto> languagesProtocols = languageConverter.convert(languages);
        List<Function<LanguageDto, String>> comparingFields = Collections.singletonList(LanguageDto::getNameLanguage);
        return filter(languagesProtocols, filter, comparingFields);

    }

    public static List<LanguageDto> filter(List<LanguageDto> allProtocols, LanguageDto filter,
                                        List<Function<LanguageDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private static boolean test(LanguageDto protocol, LanguageDto filter,
                                List<Function<LanguageDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }

}
