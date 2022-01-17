package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookDtoFilter;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface BookService {

    BookDto create(BookWithRelationIdsDto bookWithRelationIdsDto);

    BookDto getById(Long id) throws NotFoundException;

    List<BookDto> getAll(int start, int max);

    List<BookDto> getAll();

    BookDto update(BookWithRelationIdsDto bookWithRelationIdsDto);

    void deleteById(Long id);

    List<BookDto> getByFiler(String name,
                             String description,
                            String minNumberOfPage,
                            String maxNumberOfPage,
                            String minYearOfPublishing,
                            String maxYearOfPublishing,
                            String minNumberOfCopies,
                            String maxNumberOfCopies);
}
