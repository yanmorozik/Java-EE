package eu.senla.library.service;

import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.api.service.PublisherService;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public PublisherDto create(PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        Publisher response = publisherRepository.add(publisher);
        return modelMapper.map(response, PublisherDto.class);
    }

    @Override
    public PublisherDto getById(Long id) {
        Publisher response = publisherRepository.findById(id);
        return modelMapper.map(response, PublisherDto.class);
    }

    @Override
    public List<PublisherDto> getAll() {

        List<Publisher> publishers = publisherRepository.findAll();
        return modelMapper.map(publishers, new TypeToken<List<PublisherDto>>() {
        }.getType());
    }
    @Transactional
    @Override
    public PublisherDto update(PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        Publisher response = publisherRepository.update(publisher);
        return modelMapper.map(response, PublisherDto.class);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
