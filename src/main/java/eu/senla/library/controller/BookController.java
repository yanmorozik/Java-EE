package eu.senla.library.controller;

import eu.senla.library.api.service.BookService;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.ErrorMessageDto;
import eu.senla.library.exception.AuthorNotFoundException;
import eu.senla.library.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(
            BookController.class);

    private final BookService bookService;

    @ExceptionHandler(BookNotFoundException.class)
    public ErrorMessageDto catchException(){
        return new ErrorMessageDto("error");
    }

    @PostMapping
    public BookDto create(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) throws BookNotFoundException {
        return bookService.getById(id);
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PutMapping
    public BookDto update(@RequestBody BookDto bookDto) {
        return bookService.update(bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
