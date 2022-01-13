package eu.senla.library.api.service;

import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface BookService {

    BookDto create(BookWithRelationIdsDto bookWithRelationIdsDto);

    BookDto getById(Long id) throws NotFoundException;

    List<BookDto> getAll();

    BookDto update(BookWithRelationIdsDto bookWithRelationIdsDto);

    void deleteById(Long id);
}
