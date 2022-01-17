package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    BookingDto getById(Long id) throws NotFoundException;

    List<BookingDto> getAll(int start, int max);

    List<BookingDto> getAll();

    BookingDto update(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    void deleteById(Long id);

    List<BookingDto> getByFiler(String startYear,
                                String startMonth,
                                String startDay,
                                String startHour,
                                String startMinute,
                                String endYear,
                                String endMonth,
                                String endDay,
                                String endHour,
                                String endMinute);
}
