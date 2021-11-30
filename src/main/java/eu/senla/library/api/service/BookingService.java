package eu.senla.library.api.service;

import eu.senla.library.dto.BookingDto;
import eu.senla.library.exception.BookingNotFoundException;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingDto bookingDto);

    BookingDto getById(Long id) throws BookingNotFoundException;

    List<BookingDto> getAll();

    BookingDto update(BookingDto bookingDto);

    void deleteById(Long id);
}
