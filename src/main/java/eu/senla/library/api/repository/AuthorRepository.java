package eu.senla.library.api.repository;

import eu.senla.library.model.Author;
import eu.senla.library.model.Book;

import java.util.List;

public interface AuthorRepository extends AbstractRepository<Author>{

    Author add(Author author);

    Author findById(Long id);

    List<Author> findAll();

    Author update(Author author);

    void delete(Author author);

    void deleteById(Long id);
}
