package eu.senla.library.api.repository;

import eu.senla.library.model.Publisher;

import java.util.List;

public interface PublisherRepository extends AbstractRepository<Publisher>{

    Publisher add(Publisher Publisher);

    Publisher findById(Long id);

    List<Publisher> findAll();

    Publisher update(Publisher publisher);

    void delete(Publisher publisher);

    void deleteById(Long id);
}
