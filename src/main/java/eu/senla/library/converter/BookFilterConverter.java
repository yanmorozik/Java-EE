package eu.senla.library.converter;

import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookDtoFilter;
import eu.senla.library.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookFilterConverter extends Converter<Book, BookDtoFilter>{
    public BookFilterConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
