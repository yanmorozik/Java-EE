package eu.senla.library.repository;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.api.repository.CredentialRepository;
import eu.senla.library.model.Book;
import eu.senla.library.model.Credential;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialRepositoryImpl extends AbstractRepositoryImpl<Credential> implements CredentialRepository {
    public CredentialRepositoryImpl() {
        super(Credential.class);
    }
}
