package eu.senla.library.converter;

import eu.senla.library.dto.LanguageDto;
import eu.senla.library.model.Language;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanguageConverter {
    private final ModelMapper modelMapper;

    public Language convert(LanguageDto languageDto) {
        return modelMapper.map(languageDto, Language.class);
    }

    public LanguageDto convert(Language language) {
        return modelMapper.map(language, LanguageDto.class);
    }

    public List<LanguageDto> convert(List<Language> languages) {
        return modelMapper.map(languages, new TypeToken<List<LanguageDto>>() {
        }.getType());
    }
}
