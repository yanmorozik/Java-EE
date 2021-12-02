package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.api.service.UserService;
import eu.senla.library.converter.AuthorConverter;
import eu.senla.library.converter.UserConverter;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.model.Author;
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
    public void saveAuthorShouldFinishedOk() {

        final String authorName = "name";
        final String authorSurname = "surname";

        when(authorRepository.add(any())).thenReturn(Author.builder().firstName(authorName).surname(authorSurname).build());

        authorRepository.add(any());

        verify(authorRepository,times(1)).add(any());
    }
}
