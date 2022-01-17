package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface PublisherService {

    PublisherDto create(PublisherDto publisherDto);

    PublisherDto getById(Long id) throws NotFoundException;

    List<PublisherDto> getAll(int start,int max);

    List<PublisherDto> getAll();

    PublisherDto update(PublisherDto publisherDto);

    void deleteById(Long id);

    List<PublisherDto> getByFiler(String namePublisher, String telephone);

}
