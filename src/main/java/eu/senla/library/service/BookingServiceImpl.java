package eu.senla.library.service;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.api.service.BookingService;
import eu.senla.library.converter.BookingConverter;
import eu.senla.library.converter.BookingConverterWithBookWithRelationIdsDto;
import eu.senla.library.dto.BookDto;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Book;
import eu.senla.library.model.Booking;
import eu.senla.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    @Override
    public BookingDto getById(Long id) throws NotFoundException {
        Booking response = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return bookingConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDto> getAll(int start, int max) {
        List<Booking> bookings = bookingRepository.findAll(start, max);
        return bookingConverter.convert(bookings);
    }

    @Transactional(readOnly = true)
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

    @Override
    public List<BookingDto> getByFiler(String startYear,
                                       String startMonth,
                                       String startDay,
                                       String startHour,
                                       String startMinute,
                                       String endYear,
                                       String endMonth,
                                       String endDay,
                                       String endHour,
                                       String endMinute) {
        LocalDateTime startDate = LocalDateTime.of(Integer.parseInt(startYear),
                Integer.parseInt(startMonth),
                Integer.parseInt(startDay),
                Integer.parseInt(startHour),
                Integer.parseInt(startMinute));

        LocalDateTime endDate = LocalDateTime.of(Integer.parseInt(endYear),
                Integer.parseInt(endMonth),
                Integer.parseInt(endDay),
                Integer.parseInt(endHour),
                Integer.parseInt(endMinute));

        List<Booking> bookings = bookingRepository.findAll();

        List<BookingDto> bookingProtocols = bookingConverter.convert(bookings);

        bookingProtocols = bookingProtocols.stream().filter(b1->b1.getStartTime().isAfter(startDate))
                .filter(b2->b2.getStartTime().isBefore(endDate))
                .collect(Collectors.toList());

        return bookingProtocols;
    }

}
