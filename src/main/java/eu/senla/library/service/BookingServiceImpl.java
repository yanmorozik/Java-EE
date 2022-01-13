package eu.senla.library.service;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.BookingService;
import eu.senla.library.converter.BookingConverter;
import eu.senla.library.converter.BookingConverterWithBookWithRelationIdsDto;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Book;
import eu.senla.library.model.Booking;
import eu.senla.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;
    private final BookingConverterWithBookWithRelationIdsDto bookingWithRelationConverter;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public BookingDto create(BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        final Booking response = bookingRepository.add(reassignment(bookingWithRelationIdsDto));
        return bookingConverter.convert(response);
    }

    @Transactional
    @Override
    public BookingDto getById(Long id) throws NotFoundException {
        Booking response = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
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
    public BookingDto update(BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        final Booking response = bookingRepository.update(reassignment(bookingWithRelationIdsDto));
        return bookingConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking reassignment(BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        final Booking booking = bookingWithRelationConverter.convert(bookingWithRelationIdsDto);

        Book book = bookRepository.findById(bookingWithRelationIdsDto.getBookId())
                .orElseThrow(() -> new NotFoundException(bookingWithRelationIdsDto.getBookId()));
        booking.setBook(book);

        User user = userRepository.findById(bookingWithRelationIdsDto.getUserId())
                .orElseThrow(() -> new NotFoundException(bookingWithRelationIdsDto.getUserId()));
        booking.setUser(user);

        return booking;
    }
}
