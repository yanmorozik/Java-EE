package eu.senla.library.controller;

import eu.senla.library.api.service.BookService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookDtoFilter;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookDto>> getAll(@RequestParam(defaultValue = "1") int start,
                                                @RequestParam(defaultValue = "3") int max) {
        List<BookDto> books = bookService.getAll(start, max);
        return ResponseEntity.ok(books);
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

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookDto>> getByFiler(@RequestParam(defaultValue = "") String name,
                                                    @RequestParam(defaultValue = "") String description,
                                                    @RequestParam(defaultValue = "0") String minNumberOfPage,
                                                    @RequestParam(defaultValue = "9999999") String maxNumberOfPage,
                                                    @RequestParam(defaultValue = "0") String minYearOfPublishing,
                                                    @RequestParam(defaultValue = "9999999") String maxYearOfPublishing,
                                                    @RequestParam(defaultValue = "0") String minNumberOfCopies,
                                                    @RequestParam(defaultValue = "9999999") String maxNumberOfCopies) {
        List<BookDto> books = bookService.getByFiler(
                name,
                description,
                minNumberOfPage,
                maxNumberOfPage,
                minYearOfPublishing,
                maxYearOfPublishing,
                minNumberOfCopies,
                maxNumberOfCopies);
        return ResponseEntity.ok(books);
    }

}
