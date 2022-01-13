package eu.senla.library.controller;

import eu.senla.library.api.service.BookService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookDto> create(@RequestBody BookWithRelationIdsDto bookWithRelationIdsDto) {
        BookDto dto = bookService.create(bookWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookDto> getById(@PathVariable Long id) throws NotFoundException {
        BookDto dto = bookService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookDto> update(@RequestBody BookWithRelationIdsDto bookWithRelationIdsDto) {
        BookDto dto = bookService.update(bookWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
