package eu.senla.library.service;

import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.api.service.LanguageService;
import eu.senla.library.dto.LanguageDto;
import eu.senla.library.model.Language;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public LanguageDto create(LanguageDto languageDto) {
        Language language = modelMapper.map(languageDto, Language.class);
        Language response = languageRepository.add(language);
        return modelMapper.map(response, LanguageDto.class);
    }

    @Override
    public LanguageDto getById(Long id) {
        Language response = languageRepository.findById(id);
        return modelMapper.map(response, LanguageDto.class);
    }

    @Override
    public List<LanguageDto> getAll() {
        List<Language> languages = languageRepository.findAll();
        return modelMapper.map(languages, new TypeToken<List<LanguageDto>>() {
        }.getType());
    }
    @Transactional
    @Override
    public LanguageDto update(LanguageDto languageDto) {
        Language language = modelMapper.map(languageDto, Language.class);
        Language response = languageRepository.update(language);
        return modelMapper.map(response, LanguageDto.class);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
