package eu.senla.library.controller;

import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookingDto> create(@RequestBody BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        BookingDto dto = bookingService.create(bookingWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookingDto> getById(@PathVariable Long id) throws NotFoundException {
        BookingDto dto = bookingService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookingDto>> getAll() {
        List<BookingDto> bookings = bookingService.getAll();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookingDto> update(@RequestBody BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        BookingDto dto = bookingService.update(bookingWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
