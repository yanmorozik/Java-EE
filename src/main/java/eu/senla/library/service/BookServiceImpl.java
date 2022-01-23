package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.api.repository.GenreRepository;
import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.api.service.BookService;
import eu.senla.library.api.service.GenreService;
import eu.senla.library.converter.BookConverterWithBookDto;
import eu.senla.library.converter.BookConverterWithBookWithRelationIdsDto;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Author;
import eu.senla.library.model.Book;
import eu.senla.library.model.Genre;
import eu.senla.library.model.Language;
import eu.senla.library.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    //инжектится проксу уже
    //private final BookService bookService;
    private final GenreService genreService;

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
        //создание 2 транзакций
        genreService.getAll(2,2);
        return bookConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll(int start, int max) {
        List<Book> books = bookRepository.findAll(start, max);
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

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getByFiler(String name,
                                    String description,
                                    String minNumberOfPage,
                                    String maxNumberOfPage,
                                    String minYearOfPublishing,
                                    String maxYearOfPublishing,
                                    String minNumberOfCopies,
                                    String maxNumberOfCopies,
                                    int start,
                                    int max
                                    ) {
        BookDto filter = BookDto.builder().name(name).description(description).build();
        List<Book> books = bookRepository.findAll(start,max);
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

    private List<BookDto> filter(List<BookDto> allProtocols, BookDto filter,
                                             List<Function<BookDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private boolean test(BookDto protocol, BookDto filter,
                                List<Function<BookDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }

    private Book reassignment(BookWithRelationIdsDto bookWithRelationIdsDto) {
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
