package eu.senla.library.api.service;

import eu.senla.library.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingDto bookingDto);

    BookingDto getById(Long id);

    List<BookingDto> getAll();

    BookingDto update(BookingDto bookingDto);

    void delete(BookingDto bookingDto);

    void deleteById(Long id);
}
