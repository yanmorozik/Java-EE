package eu.senla.library.repository;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final List<Author> authors = new ArrayList<>();

    @Override
    public Author add(Author author) {
        authors.add(author);
        return author;
    }

    @Override
    public Author findById(Long id) {
        return authors.get(id.intValue());
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }

    @Override
    public Author update(Author author) {
        Long index = author.getId();
        authors.set(index.intValue(), author);
        return author;
    }

    @Override
    public void delete(Author author) {
        authors.remove(author);
    }

    @Override
    public void deleteById(Long id) {
        authors.remove(id.intValue());
    }
}
