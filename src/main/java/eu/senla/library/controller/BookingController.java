package eu.senla.library.controller;

import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.ErrorMessageDto;
import eu.senla.library.exception.BookingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookingController {

    private final BookingService bookingService;

    @ExceptionHandler(BookingNotFoundException.class)
    public ErrorMessageDto catchException(){
        return new ErrorMessageDto("error");
    }

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
