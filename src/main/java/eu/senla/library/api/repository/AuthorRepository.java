package eu.senla.library.api.repository;

import eu.senla.library.model.Author;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AuthorRepository extends AbstractRepository<Author>{
    Author getByName(String name);
}
