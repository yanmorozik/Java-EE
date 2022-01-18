package eu.senla.library.converter;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter extends Converter<Author, AuthorDto> {
    public AuthorConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
