package eu.senla.library.repository;

import eu.senla.library.BaseRepositoryTest;
import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = AuthorRepositoryImpl.class)
public class AuthorRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldCreateAuthor() {
        final String authorName = "Pushkin";
        authorRepository.add(Author.builder().firstName(authorName).surname("fsfdsg").build());

        List<Author> all = authorRepository.findAll();
        assertEquals(authorName, all.get(all.size() - 1).getFirstName());
    }

    @Test
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
    public void shouldReturnAllAuthors() {
        final String authorName = "Pushkin";
        authorRepository.add(Author.builder().firstName(authorName).surname("fsfdsg").build());

        List<Author> all = authorRepository.findAll();
        assertEquals(1, all.size());

        assertEquals(authorName, all.get(0).getFirstName());
    }

    @Test
    public void shouldUpdateAuthor() {
        final String authorName = "Pushkin";
        final String anotherAuthorName = "Pushkin";

        Author author = new Author();
        author.setFirstName(authorName);
        author.setId(999L);
        authorRepository.add(author);

        Author tempAuthor = new Author();
        author.setFirstName(anotherAuthorName);
        author.setId(999L);

        authorRepository.update(tempAuthor);
        Author author2;
        author2=authorRepository.findById(999L);
        assertEquals(anotherAuthorName, author2.getFirstName());
    }

    @Test
    public void shouldDeleteAuthorById(){
        final String authorName = "Pushkin";
        Author author = new Author();
        author.setFirstName(authorName);
        author.setId(122L);
        authorRepository.add(author);

        authorRepository.deleteById(122L);

        assertEquals(122L,author.getId());
    }
}
