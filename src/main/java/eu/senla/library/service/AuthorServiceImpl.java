package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.model.Author;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    Author author;

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        author = modelMapper.map(authorDto, Author.class);
        Author response = authorRepository.add(author);
        return modelMapper.map(response, AuthorDto.class);
    }

    @Override
    public AuthorDto getById(Long id) {
        Author response = authorRepository.findById(id);
        return modelMapper.map(response, AuthorDto.class);
    }

    @Override
    public List<AuthorDto> getAll() {

        List<Author> authors = authorRepository.findAll();
        return modelMapper.map(authors, new TypeToken<List<AuthorDto>>() {
        }.getType());
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        author = modelMapper.map(authorDto, Author.class);
        Author response = authorRepository.update(author);
        return modelMapper.map(response, AuthorDto.class);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
