package eu.senla.library.converter;

import eu.senla.library.dto.BookingWithRelationIdsDto;
import eu.senla.library.dto.UserWithRelationIdsDto;
import eu.senla.library.model.Booking;
import eu.senla.library.model.User;
import org.modelmapper.ModelMapper;

public class UserConverterWithBookWithRelationIdsDto extends Converter<User, UserWithRelationIdsDto>{
    public UserConverterWithBookWithRelationIdsDto(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
