package eu.senla.library.converter;

import eu.senla.library.dto.BookWithRelationIdsDto;
import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.model.Book;
import eu.senla.library.model.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingConverterWithBookWithRelationIdsDto extends Converter<Booking, BookingWithRelationIdsDto>{
    public BookingConverterWithBookWithRelationIdsDto(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
