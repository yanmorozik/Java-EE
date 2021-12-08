package eu.senla.library.service;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.converter.AuthorConverter;
import eu.senla.library.dto.AuthorDto;
import eu.senla.library.exception.AuthorNotFoundException;
import eu.senla.library.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorConverter authorConverter;

    @Mock
    private AuthorRepository authorRepository;

    private final Author author = Author.builder().id(1L).firstName("yan").surname("morozik").build();
    private final AuthorDto authorDto = AuthorDto.builder().id(1L).firstName("yan").surname("morozik").build();

    @Test
    public void createAuthorShouldFinishedOk() {

        doReturn(author).when(authorConverter).convert((AuthorDto) any());
        doReturn(authorDto).when(authorConverter).convert((Author) any());

        authorService.create(authorDto);
        verify(authorRepository, times(1)).add(author);
    }

    @Test
    public void getByIdAuthorShouldFinishedOk() throws AuthorNotFoundException {

        doReturn(author).when(authorConverter).convert((AuthorDto) any());
        doReturn(authorDto).when(authorConverter).convert((Author) any());
        when(authorRepository.findById(any())).thenReturn(Author.builder().id(1L).firstName("yan").surname("morozik").build());

        final AuthorDto authorDto1 = authorService.getById(1L);

        assertEquals(authorDto, authorDto1);
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void getAllAuthorShouldFinishedOk() {
        List<AuthorDto> authorDtos = new ArrayList<>();

        doReturn(author).when(authorConverter).convert((AuthorDto) any());
        doReturn(authorDto).when(authorConverter).convert((Author) any());
        doReturn(authorDtos).when(authorConverter).convert(Collections.singletonList(any()));

        final List<AuthorDto> authors = authorService.getAll();

        assertEquals(authorDtos, authors);
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void updateAuthorShouldFinishedOk() {

        doReturn(author).when(authorConverter).convert((AuthorDto) any());
        doReturn(authorDto).when(authorConverter).convert((Author) any());
        when(authorRepository.update(any())).thenReturn(Author.builder().id(2L).firstName("yan").surname("morozik").build());

        final AuthorDto authorDto1 = authorService.update(authorDto);

        assertEquals(1L, authorDto1.getId());
    }

    @Test
    public void deleteByIdAuthorShouldFinishedOk() {

        doNothing().when(authorRepository).deleteById(any());
        authorService.deleteById(1L);
        verify(authorRepository, times(1)).deleteById(1L);
    }
}
