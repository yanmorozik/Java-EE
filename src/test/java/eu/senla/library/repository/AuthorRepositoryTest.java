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

@ContextConfiguration(classes = AuthorRepositoryImpl.class)
public class AuthorRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Transactional
    public void shouldCreateAuthor() {
        final String authorName = "Pushkin";
        authorRepository.add(Author.builder().firstName(authorName).surname("fsfdsg").build());

        List<Author> all = authorRepository.findAll();
        assertEquals(authorName, all.get(all.size() - 1).getFirstName());
    }


    //for testing, we must remove @GeneratedValue (strategy = GenerationType.IDENTITY) in BaseEntity
    @Test
    @Transactional
    public void shouldReturnAuthorById() {
        final String authorName = "Pushkin";
        Author author = new Author();
        author.setFirstName(authorName);
        author.setId(122L);
        authorRepository.add(author);

        Author tempAuthor = authorRepository.findById(122L);

        assertEquals(authorName, tempAuthor.getFirstName());
    }

    @Test
    @Transactional
    public void shouldReturnAllAuthors() {
        final String authorName = "Pushkin";
        authorRepository.add(Author.builder().firstName(authorName).surname("fsfdsg").build());

        List<Author> all = authorRepository.findAll();
        assertEquals(1, all.size());

        assertEquals(authorName, all.get(0).getFirstName());
    }

    @Test
    @Transactional
    public void shouldUpdateAuthor() {
        final String authorName = "Pushkin";

        Author author = new Author();
        author.setFirstName(authorName);
        author.setId(55L);
        authorRepository.add(author);

        Author tempAuthor = new Author();
        author.setFirstName(authorName);
        author.setId(55L);

        Author author2;

        author2 = authorRepository.update(tempAuthor);

        assertEquals(authorName, author2.getFirstName());
    }

    //for testing, we must remove @GeneratedValue (strategy = GenerationType.IDENTITY) in BaseEntity
    @Test
    @Transactional
    public void shouldDeleteAuthorById() {
        final String authorName = "Pushkin";
        Author author = new Author();
        author.setFirstName(authorName);
        author.setId(122L);
        authorRepository.add(author);

        authorRepository.deleteById(122L);

        assertEquals(122L, author.getId());
    }
}
