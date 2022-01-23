package eu.senla.library.converter;

import eu.senla.library.dto.BookingDto;
import eu.senla.library.model.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter extends Converter<Booking, BookingDto> {
    public BookingConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
