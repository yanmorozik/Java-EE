package eu.senla.library.converter;

import eu.senla.library.dto.BookDto;
import eu.senla.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookConverter {
    private final ModelMapper modelMapper;

    public Book convert(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public BookDto convert(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> convert(List<Book> books) {
        return modelMapper.map(books, new TypeToken<List<BookDto>>() {
        }.getType());
    }
}
