package eu.senla.library.converter;

import eu.senla.library.dto.BookDto;
import eu.senla.library.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends Converter<Book,BookDto> {
    public BookConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
