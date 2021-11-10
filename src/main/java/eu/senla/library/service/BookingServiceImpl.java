package eu.senla.library.service;

import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.model.Booking;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    Booking booking;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        booking = modelMapper.map(bookingDto, Booking.class);
        Booking response = bookingRepository.add(booking);
        return modelMapper.map(response, BookingDto.class);
    }

    @Override
    public BookingDto getById(Long id) {
        Booking response = bookingRepository.findById(id);
        return modelMapper.map(response, BookingDto.class);
    }

    @Override
    public List<BookingDto> getAll() {

        List<Booking> bookings = bookingRepository.findAll();
        return modelMapper.map(bookings, new TypeToken<List<BookingDto>>() {
        }.getType());
    }

    @Override
    public BookingDto update(BookingDto bookingDto) {
        booking = modelMapper.map(bookingDto, Booking.class);
        Booking response = bookingRepository.update(booking);
        return modelMapper.map(response, BookingDto.class);
    }

    @Override
    public void delete(BookingDto bookingDto) {

    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
