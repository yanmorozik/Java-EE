package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.AuthorConverter;
import eu.senla.library.converter.UserConverter;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.AuthorNotFoundException;
import eu.senla.library.model.Author;
import eu.senla.library.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class AuthorServiceTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorConverter authorConverter;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void createAuthorShouldFinishedOk() {

        authorRepository.add(any());

        verify(authorRepository,times(1)).add(any());
    }

    @Test
    public void getByIdAuthorShouldFinishedOk(){
        authorRepository.findById(any());
        verify(authorRepository,times(1)).findById(any());
    }

    @Test
    public void getAllAuthorShouldFinishedOk(){
        authorRepository.findAll();
        verify(authorRepository,times(1)).findAll();
    }

    @Test
    public void updateAuthorShouldFinishedOk(){
        authorRepository.update(any());
        verify(authorRepository,times(1)).update(any());
    }

    @Test
    public void deleteByIdAuthorShouldFinishedOk(){
        authorRepository.deleteById(any());
        verify(authorRepository,times(1)).deleteById(any());
    }
}
