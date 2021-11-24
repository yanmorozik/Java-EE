package eu.senla.library.repository;

import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.model.Language;
import eu.senla.library.model.Publisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PublisherRepositoryImpl extends AbstractRepositoryImpl<Publisher> implements PublisherRepository {

    public PublisherRepositoryImpl() {
        super(Publisher.class);
    }
}
