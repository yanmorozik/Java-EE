package eu.senla.library.converter;

import eu.senla.library.dto.LanguageDto;
import eu.senla.library.model.Language;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LanguageConverter extends Converter<Language,LanguageDto>{
    public LanguageConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
