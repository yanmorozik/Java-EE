package eu.senla.library.converter;

import eu.senla.library.dto.BookingDto;
import eu.senla.library.model.Booking;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingConverter {
    private final ModelMapper modelMapper;

    public Booking convert(BookingDto bookingDto) {
        return modelMapper.map(bookingDto, Booking.class);
    }

    public BookingDto convert(Booking booking) {
        return modelMapper.map(booking, BookingDto.class);
    }

    public List<BookingDto> convert(List<Booking> bookings) {
        return modelMapper.map(bookings, new TypeToken<List<BookingDto>>() {
        }.getType());
    }
}
