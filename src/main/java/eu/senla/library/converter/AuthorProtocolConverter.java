package eu.senla.library.converter;

import eu.senla.library.filter.AuthorsProtocol;
import eu.senla.library.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorProtocolConverter extends Converter<Author, AuthorsProtocol>{

    public AuthorProtocolConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
