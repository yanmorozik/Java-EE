package eu.senla.library.service;

import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.api.service.PublisherService;
import eu.senla.library.converter.PublisherConverter;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherConverter publisherConverter;

    @Transactional
    @Override
    public PublisherDto create(PublisherDto publisherDto) {
        final Publisher publisher = publisherConverter.convert(publisherDto);
        final Publisher response = publisherRepository.add(publisher);
        return publisherConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public PublisherDto getById(Long id) throws NotFoundException {
        Publisher response = publisherRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return publisherConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PublisherDto> getAll() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publisherConverter.convert(publishers);
    }

    @Transactional
    @Override
    public PublisherDto update(PublisherDto publisherDto) {
        final Publisher publisher = publisherConverter.convert(publisherDto);
        final Publisher response = publisherRepository.update(publisher);
        return publisherConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
