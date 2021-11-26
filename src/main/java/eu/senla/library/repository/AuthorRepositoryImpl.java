package eu.senla.library.repository;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl extends AbstractRepositoryImpl<Author> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
