package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorDto authorDto);

    AuthorDto getById(Long id) throws NotFoundException;

    List<AuthorDto> getAll(int start, int max);

    List<AuthorDto> getAll();

    AuthorDto update(AuthorDto authorDto);

    void deleteById(Long id);

    List<AuthorDto> getByFiler(String firstName, String surname);
}
