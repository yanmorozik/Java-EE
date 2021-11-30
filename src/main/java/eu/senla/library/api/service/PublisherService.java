package eu.senla.library.api.service;

import eu.senla.library.dto.PublisherDto;
import eu.senla.library.exception.PublisherNotFoundException;

import java.util.List;

public interface PublisherService {

    PublisherDto create(PublisherDto publisherDto);

    PublisherDto getById(Long id) throws PublisherNotFoundException;

    List<PublisherDto> getAll();

    PublisherDto update(PublisherDto publisherDto);

    void deleteById(Long id);
}
