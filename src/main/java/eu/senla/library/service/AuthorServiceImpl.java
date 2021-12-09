package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.converter.AuthorConverter;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    @Transactional
    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = authorConverter.convert(authorDto);
        Author response = authorRepository.add(author);
        return authorConverter.convert(response);
    }

    @Transactional
    @Override
    public AuthorDto getById(Long id) throws NotFoundException {
        Author response = Optional.ofNullable(authorRepository.findById(id)).orElseThrow(()-> new NotFoundException(id));
        return authorConverter.convert(response);
    }

    @Transactional
    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authorConverter.convert(authors);
    }

    @Transactional
    @Override
    public AuthorDto update(AuthorDto authorDto) {
        final Author author = authorConverter.convert(authorDto);
        final Author response = authorRepository.update(author);
        return authorConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
