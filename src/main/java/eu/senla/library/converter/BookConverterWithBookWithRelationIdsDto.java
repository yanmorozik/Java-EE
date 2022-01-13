package eu.senla.library.converter;

import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookConverterWithBookWithRelationIdsDto extends Converter<Book, BookWithRelationIdsDto> {
    public BookConverterWithBookWithRelationIdsDto(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
