package eu.senla.library.converter;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.filter.AuthorsProtocol;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter extends Converter<AuthorDto, AuthorsProtocol>{
    public AuthorDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
