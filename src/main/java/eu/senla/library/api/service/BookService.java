package eu.senla.library.api.service;

import eu.senla.library.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto create(BookDto bookDto);

    BookDto getById(Long id);

    List<BookDto> getAll();

    BookDto update(BookDto bookDto);

    void deleteById(Long id);
}
