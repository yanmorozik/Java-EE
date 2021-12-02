package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.AuthorConverter;
import eu.senla.library.converter.UserConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith({MockitoExtension.class})
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Spy
    private AuthorConverter authorConverter;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void saveAuthorShouldFinishedOk(){
        //when(authorRepository.add(any())).thenReturn(Author)
    }
}
