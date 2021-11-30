package eu.senla.library.service;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.api.service.BookService;
import eu.senla.library.converter.BookConverter;
import eu.senla.library.dto.BookDto;
import eu.senla.library.exception.BookNotFoundException;
import eu.senla.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Transactional
    @Override
    public BookDto create(BookDto bookDto) {
        final Book book = bookConverter.convert(bookDto);
        final Book response = bookRepository.add(book);
        return bookConverter.convert(response);
    }

    @Transactional
    @Override
    public BookDto getById(Long id) throws BookNotFoundException {
        Book response = Optional.ofNullable(bookRepository.findById(id)).orElseThrow(() -> new BookNotFoundException(id));
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
    public BookDto update(BookDto bookDto) {
        final Book book = bookConverter.convert(bookDto);
        final Book response = bookRepository.update(book);
        return bookConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
