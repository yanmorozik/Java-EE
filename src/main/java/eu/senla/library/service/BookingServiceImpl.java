package eu.senla.library.service;

import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.api.service.BookingService;
import eu.senla.library.converter.BookingConverter;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Author;
import eu.senla.library.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;

    @Transactional
    @Override
    public BookingDto create(BookingDto bookingDto) {
        final Booking booking = bookingConverter.convert(bookingDto);
        final Booking response = bookingRepository.add(booking);
        return bookingConverter.convert(response);
    }

    @Transactional
    @Override
    public BookingDto getById(Long id) throws NotFoundException {
        Booking response = bookingRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return bookingConverter.convert(response);
    }

    @Transactional
    @Override
    public List<BookingDto> getAll() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingConverter.convert(bookings);
    }

    @Transactional
    @Override
    public BookingDto update(BookingDto bookingDto) {
        final Booking booking = bookingConverter.convert(bookingDto);
        final Booking response = bookingRepository.update(booking);
        return bookingConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

}
