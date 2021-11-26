package eu.senla.library.repository;

import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.model.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherRepositoryImpl extends AbstractRepositoryImpl<Publisher> implements PublisherRepository {

    public PublisherRepositoryImpl() {
        super(Publisher.class);
    }
}
