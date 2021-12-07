package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.AuthorNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class AuthorServiceTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void createAuthorShouldFinishedOk() {
        authorService.create(new AuthorDto());
        verify(authorRepository,times(1)).add(any());
    }

    @Test
    public void getByIdAuthorShouldFinishedOk(){
        try {
            authorService.getById(any());
        } catch (AuthorNotFoundException authorNotFoundException) {
            authorNotFoundException.printStackTrace();
        }
        verify(authorRepository,times(1)).findById(any());
    }

    @Test
    public void getAllAuthorShouldFinishedOk(){
        authorService.getAll();
        verify(authorRepository,times(1)).findAll();
    }

    @Test
    public void updateAuthorShouldFinishedOk(){
        authorService.update(any());
        verify(authorRepository,times(1)).update(any());
    }

    @Test
    public void deleteByIdAuthorShouldFinishedOk(){
        authorService.deleteById(any());
        verify(authorRepository,times(1)).deleteById(any());
    }
}
