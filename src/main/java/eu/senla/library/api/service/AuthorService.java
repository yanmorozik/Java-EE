package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorDto authorDto);

    AuthorDto getById(Long id);

    List<AuthorDto> getAll();

    AuthorDto update(AuthorDto authorDto);

    void delete(AuthorDto authorDto);

    void deleteById(Long id);
}
