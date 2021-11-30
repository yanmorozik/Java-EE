package eu.senla.library.converter;

import eu.senla.library.dto.PublisherDto;
import eu.senla.library.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PublisherConverter {
    private final ModelMapper modelMapper;

    public Publisher convert(PublisherDto publisherDto) {
        return modelMapper.map(publisherDto, Publisher.class);
    }

    public PublisherDto convert(Publisher publisher) {
        return modelMapper.map(publisher, PublisherDto.class);
    }

    public List<PublisherDto> convert(List<Publisher> publishers) {
        return modelMapper.map(publishers, new TypeToken<List<PublisherDto>>() {
        }.getType());
    }
}
