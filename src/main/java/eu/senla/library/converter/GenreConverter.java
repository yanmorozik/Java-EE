package eu.senla.library.converter;

import eu.senla.library.dto.GenreDto;
import eu.senla.library.model.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter extends Converter<Genre,GenreDto>{
    public GenreConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
