package eu.senla.library.repository;

import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.model.Publisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

    private final List<Publisher> publishers = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }
    @Override
    public Publisher add(Publisher publisher) {
        publisher.setId(generateFacilitiesId());
        publishers.add(publisher);
        return publisher;
    }

    @Override
    public Publisher findById(Long id) {
        return publishers.get(id.intValue());
    }

    @Override
    public List<Publisher> findAll() {
        return publishers;
    }

    @Override
    public Publisher update(Publisher publisher) {
        Long index = publisher.getId();
        publishers.set(index.intValue(), publisher);
        return publisher;
    }

    @Override
    public void delete(Publisher publisher) {
        publishers.remove(publisher);
    }

    @Override
    public void deleteById(Long id) {
        publishers.remove(id.intValue());
    }
}
