package eu.senla.library.service;

import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.api.service.PublisherService;
import eu.senla.library.converter.PublisherConverter;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Author;
import eu.senla.library.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public List<PublisherDto> getAll(int start,int max) {
        List<Publisher> publishers = publisherRepository.findAll(start,max);
        return publisherConverter.convert(publishers);
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

    @Transactional
    @Override
    public List<PublisherDto> getByFiler(String namePublisher, String telephone) {

        PublisherDto filter = PublisherDto.builder().namePublisher(namePublisher).telephone(telephone).build();
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherDto> publisherProtocols = publisherConverter.convert(publishers);
        List<Function<PublisherDto, String>> comparingFields = Arrays.asList(PublisherDto::getNamePublisher,
                PublisherDto::getTelephone);
        return filter(publisherProtocols, filter, comparingFields);

    }

    public static List<PublisherDto> filter(List<PublisherDto> allProtocols, PublisherDto filter,
                                         List<Function<PublisherDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private static boolean test(PublisherDto protocol, PublisherDto filter,
                                List<Function<PublisherDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }
}
