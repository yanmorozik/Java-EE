package eu.senla.library.controller;

import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.exception.BookingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(
            BookingController.class);

    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@RequestBody BookingDto bookingDto) {
        return bookingService.create(bookingDto);
    }

    @GetMapping("/{id}")
    public BookingDto getById(@PathVariable Long id) throws BookingNotFoundException {
        return bookingService.getById(id);
    }

    @GetMapping
    public List<BookingDto> getAll() {
        return bookingService.getAll();
    }

    @PutMapping
    public BookingDto update(@RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
    }

}
