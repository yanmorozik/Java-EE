package eu.senla.library.repository;

import eu.senla.library.BaseRepositoryTest;
import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = AuthorRepositoryImpl.class)
public class AuthorRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Transactional
    public void shouldCreateAuthor() {
        authorRepository.add(Author.builder().firstName("name").surname("surname").build());
        List<Author> all = authorRepository.findAll(0,1);
        assertEquals("name", all.get(all.size() - 1).getFirstName());
    }

    @Test
    @Transactional
    public void shouldReturnAuthorById() {
        Author tempAuthor = authorRepository.add(Author.builder().firstName("name").build());
        assertEquals(tempAuthor.getId(), authorRepository.getByName("name").getId());
    }

    @Test
    @Transactional
    public void shouldReturnAllAuthors() {
        authorRepository.add(Author.builder().firstName("name").surname("surname").build());
        List<Author> all = authorRepository.findAll(0,1);
        assertEquals(1, all.size());
        assertEquals("name", all.get(0).getFirstName());
    }

    @Test
    @Transactional
    public void shouldUpdateAuthor() {
        authorRepository.add(Author.builder().firstName("name").build());
        Author author2 = authorRepository.update(Author.builder().firstName("name").build());
        assertEquals("name", author2.getFirstName());
    }

    @Test
    @Transactional
    public void shouldDeleteAuthorById() {
        authorRepository.add(Author.builder().firstName("name").build());
        authorRepository.deleteById(1L);
        assertThrows(Exception.class, () -> {
            authorRepository.getByName("name");
        });
    }
}
