package eu.senla.library.service;

import eu.senla.library.api.repository.*;
import eu.senla.library.api.service.BookService;
import eu.senla.library.converter.BookConverterWithBookDto;
import eu.senla.library.converter.BookConverterWithBookWithRelationIdsDto;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookConverterWithBookDto bookConverter;
    private final BookConverterWithBookWithRelationIdsDto bookWithRelationConverter;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final LanguageRepository languageRepository;

    @Transactional
    @Override
    public BookDto create(BookWithRelationIdsDto bookWithRelationIdsDto) {
        final Book response = bookRepository.add(reassignment(bookWithRelationIdsDto));
        return bookConverter.convert(response);
    }

    @Transactional
    @Override
    public BookDto getById(Long id) throws NotFoundException {
        Book response = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return bookConverter.convert(response);
    }

    @Transactional
    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        return bookConverter.convert(books);
    }

    @Transactional
    @Override
    public BookDto update(BookWithRelationIdsDto bookWithRelationIdsDto) {
        final Book response = bookRepository.update(reassignment(bookWithRelationIdsDto));
        return bookConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book reassignment(BookWithRelationIdsDto bookWithRelationIdsDto){
        final Book book = bookWithRelationConverter.convert(bookWithRelationIdsDto);

        Set<Author> authors = bookWithRelationIdsDto.getAuthorIds()
                .stream()
                .map(id -> authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .collect(Collectors.toSet());
        book.setAuthors(authors);

        Genre genre = genreRepository.findById(bookWithRelationIdsDto.getGenreId())
                .orElseThrow(() -> new NotFoundException(bookWithRelationIdsDto.getGenreId()));
        book.setGenre(genre);

        Set<Publisher> publishers = bookWithRelationIdsDto.getPublisherIds()
                .stream()
                .map(id -> publisherRepository.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .collect(Collectors.toSet());
        book.setPublishers(publishers);

        Language language = languageRepository.findById(bookWithRelationIdsDto.getLanguageId())
                .orElseThrow(() -> new NotFoundException(bookWithRelationIdsDto.getLanguageId()));
        book.setLanguage(language);

        return book;
    }
}
