package eu.senla.library.controller;

import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<BookingDto>> getAll(@RequestParam(defaultValue = "1") int start,
                                                   @RequestParam(defaultValue = "3") int max) {
        List<BookingDto> bookings = bookingService.getAll(start, max);
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

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookingDto>> getByFiler(@RequestParam(defaultValue = "1") String startYear,
                                                       @RequestParam(defaultValue = "1") String startMonth,
                                                       @RequestParam(defaultValue = "1") String startDay,
                                                       @RequestParam(defaultValue = "1") String startHour,
                                                       @RequestParam(defaultValue = "1") String startMinute,
                                                       @RequestParam(defaultValue = "2099") String endYear,
                                                       @RequestParam(defaultValue = "12") String endMonth,
                                                       @RequestParam(defaultValue = "31") String endDay,
                                                       @RequestParam(defaultValue = "23") String endHour,
                                                       @RequestParam(defaultValue = "59") String endMinute,
                                                       @RequestParam(defaultValue = "1") int start,
                                                       @RequestParam(defaultValue = "3") int max) {
        List<BookingDto> bookings = bookingService.getByFiler(startYear,
                startMonth,
                startDay,
                startHour,
                startMinute,
                endYear,
                endMonth,
                endDay,
                endHour,
                endMinute,
                start,
                max);
        return ResponseEntity.ok(bookings);
    }
}
