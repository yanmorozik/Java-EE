package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorDto authorDto);

    AuthorDto getById(Long id) throws AuthorNotFoundException;

    List<AuthorDto> getAll();

    AuthorDto update(AuthorDto authorDto);

    void deleteById(Long id);
}
