package eu.senla.library.service;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.api.service.BookService;
import eu.senla.library.dto.BookDto;
import eu.senla.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    Book book;

    @Override
    public BookDto create(BookDto bookDto) {
        book = modelMapper.map(bookDto, Book.class);// маппим bookDto в book
        Book response = bookRepository.add(book);
        return modelMapper.map(response, BookDto.class);//маппи book в bookDto
    }

    @Override
    public BookDto getById(Long id) {
        Book response = bookRepository.findById(id);
        return modelMapper.map(response, BookDto.class);//маппим book в bookDto
    }

    @Override
    public List<BookDto> getAll() {

        List<Book> books = bookRepository.findAll();
        return modelMapper.map(books, new TypeToken<List<BookDto>>() {
        }.getType());//маппим из List<Book> в List<BookDto>
    }


    @Override
    public BookDto update(BookDto bookDto) {
        book = modelMapper.map(bookDto, Book.class);
        Book response = bookRepository.update(book);
        return modelMapper.map(response, BookDto.class);
    }

    @Override
    public void delete(BookDto bookDto) {

    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
