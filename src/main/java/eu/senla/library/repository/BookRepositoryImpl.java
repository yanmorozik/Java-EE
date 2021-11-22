package eu.senla.library.repository;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl extends AbstractRepositoryImpl<Book> implements BookRepository {

    private final List<Book> books = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }

    @Override
    public Book add(Book book) {
        book.setId(generateFacilitiesId());
        books.add(book);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return books.get(id.intValue());
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book update(Book book) {
        Long index = book.getId();
        books.set(index.intValue(), book);
        return book;
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public void deleteById(Long id) {
        books.remove(id.intValue());
    }
}
