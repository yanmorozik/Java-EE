package eu.senla.library.service;

import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import eu.senla.library.model.Booking;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public BookingDto create(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        Booking response = bookingRepository.add(booking);
        return modelMapper.map(response, BookingDto.class);
    }

    @Transactional
    @Override
    public BookingDto getById(Long id) {
        Booking response = bookingRepository.findById(id);
        return modelMapper.map(response, BookingDto.class);
    }

    @Transactional
    @Override
    public List<BookingDto> getAll() {

        List<Booking> bookings = bookingRepository.findAll();
        return modelMapper.map(bookings, new TypeToken<List<BookingDto>>() {
        }.getType());
    }

    @Transactional
    @Override
    public BookingDto update(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        Booking response = bookingRepository.update(booking);
        return modelMapper.map(response, BookingDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
