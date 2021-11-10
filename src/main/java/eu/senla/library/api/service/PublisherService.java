package eu.senla.library.api.service;

import eu.senla.library.dto.PublisherDto;

import java.util.List;

public interface PublisherService {

    PublisherDto create(PublisherDto publisherDto);

    PublisherDto getById(Long id);

    List<PublisherDto> getAll();

    PublisherDto update(PublisherDto publisherDto);

    void delete(PublisherDto publisherDto);

    void deleteById(Long id);
}
