package eu.senla.library.service;

import eu.senla.library.api.repository.*;
import eu.senla.library.api.service.BookService;
import eu.senla.library.converter.BookConverterWithBookDto;
import eu.senla.library.converter.BookConverterWithBookWithRelationIdsDto;
import eu.senla.library.converter.BookFilterConverter;
import eu.senla.library.dto.*;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
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

    @Transactional(readOnly = true)
    @Override
    public BookDto getById(Long id) throws NotFoundException {
        Book response = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return bookConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll(int start, int max) {
        List<Book> books = bookRepository.findAll(start, max);
        return bookConverter.convert(books);
    }

    @Transactional(readOnly = true)
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

    @Override
    public List<BookDto> getByFiler(String name,
                                    String description,
                                    String minNumberOfPage,
                                    String maxNumberOfPage,
                                    String minYearOfPublishing,
                                    String maxYearOfPublishing,
                                    String minNumberOfCopies,
                                    String maxNumberOfCopies
                                    ) {
        BookDto filter = BookDto.builder().name(name).description(description).build();
        List<Book> books = bookRepository.findAll();
        List<BookDto> booksProtocols = bookConverter.convert(books);
        booksProtocols = booksProtocols.stream().filter(b1 -> b1.getNumberOfPage() >= Integer.parseInt(minNumberOfPage))
                .filter(b2 -> b2.getNumberOfPage() <= Integer.parseInt(maxNumberOfPage))
                .filter(b3 -> b3.getYearOfPublishing() >= Integer.parseInt(minYearOfPublishing))
                .filter(b4 -> b4.getYearOfPublishing() <= Integer.parseInt(maxYearOfPublishing))
                .filter(b5 -> b5.getNumberOfCopies() >= Integer.parseInt(minNumberOfCopies))
                .filter(b6 -> b6.getNumberOfCopies() <= Integer.parseInt(maxNumberOfCopies))
                .collect(Collectors.toList());

        List<Function<BookDto, String>> comparingFields = Arrays.asList(BookDto::getName,
                BookDto::getDescription);
        return filter(booksProtocols, filter, comparingFields);

    }

    public static List<BookDto> filter(List<BookDto> allProtocols, BookDto filter,
                                             List<Function<BookDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private static boolean test(BookDto protocol, BookDto filter,
                                List<Function<BookDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }

    public Book reassignment(BookWithRelationIdsDto bookWithRelationIdsDto) {
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
