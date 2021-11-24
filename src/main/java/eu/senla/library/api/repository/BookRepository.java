package eu.senla.library.api.repository;

import eu.senla.library.model.Book;

import java.util.List;

public interface BookRepository extends AbstractRepository<Book>{

    Book add(Book book);

    Book findById(Long id);

    List<Book> findAll();

    Book update(Book book);

    void delete(Book book);

    void deleteById(Long id);
}
