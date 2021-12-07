package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.converter.AuthorConverter;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.AuthorNotFoundException;
import eu.senla.library.model.Author;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        Author response = authorRepository.add(author);
        return modelMapper.map(response, AuthorDto.class);
    }

    @Transactional
    @Override
    public AuthorDto getById(Long id) throws AuthorNotFoundException {
        Author response = Optional.ofNullable(authorRepository.findById(id)).orElseThrow(()-> new AuthorNotFoundException(id));
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
