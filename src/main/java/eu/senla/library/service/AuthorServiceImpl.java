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

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    @Override
    public AuthorDto getById(Long id) throws NotFoundException {
        Author response = authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return authorConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> getAll(int start, int max) {
        List<Author> authors = authorRepository.findAll(start, max);
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

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> getByFiler(String firstName, String surname,int start, int max) {

        AuthorDto filter = AuthorDto.builder().firstName(firstName).surname(surname).build();
        List<Author> authors = authorRepository.findAll(start,max);
        List<AuthorDto> authorsProtocols = authorConverter.convert(authors);
        List<Function<AuthorDto, String>> comparingFields = Arrays.asList(AuthorDto::getFirstName,
                AuthorDto::getSurname);
        return filter(authorsProtocols, filter, comparingFields);

    }

    private List<AuthorDto> filter(List<AuthorDto> allProtocols, AuthorDto filter,
                                         List<Function<AuthorDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private boolean test(AuthorDto protocol, AuthorDto filter,
                                List<Function<AuthorDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }
}
