package eu.senla.library.api.service;

import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    BookingDto getById(Long id) throws NotFoundException;

    List<BookingDto> getAll();

    BookingDto update(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    void deleteById(Long id);
}
