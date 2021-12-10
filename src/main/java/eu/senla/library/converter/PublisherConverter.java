package eu.senla.library.converter;

import eu.senla.library.dto.PublisherDto;
import eu.senla.library.model.Publisher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter extends Converter<Publisher,PublisherDto>{
    public PublisherConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
