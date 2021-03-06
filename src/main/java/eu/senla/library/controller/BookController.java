package eu.senla.library.controller;

import eu.senla.library.api.service.BookService;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public BookDto create(@RequestBody BookWithRelationIdsDto bookWithRelationIdsDto) {
        return bookService.create(bookWithRelationIdsDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public BookDto getById(@PathVariable Long id) throws NotFoundException {
        return bookService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDto> getAll(@RequestParam(defaultValue = "1") int start,
                                                @RequestParam(defaultValue = "3") int max) {
        return bookService.getAll(start, max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto update(@RequestBody BookWithRelationIdsDto bookWithRelationIdsDto) {
        return bookService.update(bookWithRelationIdsDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDto> getByFiler(@RequestParam(defaultValue = "") String name,
                                                    @RequestParam(defaultValue = "") String description,
                                                    @RequestParam(defaultValue = "0") String minNumberOfPage,
                                                    @RequestParam(defaultValue = "9999999") String maxNumberOfPage,
                                                    @RequestParam(defaultValue = "0") String minYearOfPublishing,
                                                    @RequestParam(defaultValue = "9999999") String maxYearOfPublishing,
                                                    @RequestParam(defaultValue = "0") String minNumberOfCopies,
                                                    @RequestParam(defaultValue = "9999999") String maxNumberOfCopies,
                                                    @RequestParam(defaultValue = "1") int start,
                                                    @RequestParam(defaultValue = "3") int max) {
        return bookService.getByFiler(
                name,
                description,
                minNumberOfPage,
                maxNumberOfPage,
                minYearOfPublishing,
                maxYearOfPublishing,
                minNumberOfCopies,
                maxNumberOfCopies,
                start,
                max);
    }
}
