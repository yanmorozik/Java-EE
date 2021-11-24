package eu.senla.library.repository;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl extends AbstractRepositoryImpl<Book> implements BookRepository {

    public BookRepositoryImpl() {
        super(Book.class);
    }
}
