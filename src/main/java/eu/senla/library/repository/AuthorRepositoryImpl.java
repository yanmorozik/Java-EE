package eu.senla.library.repository;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.model.Author;
import eu.senla.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepositoryImpl extends AbstractRepositoryImpl<Author> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
