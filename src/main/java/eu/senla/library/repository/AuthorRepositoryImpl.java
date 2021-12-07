package eu.senla.library.repository;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.model.Author;
import eu.senla.library.model.Author_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class AuthorRepositoryImpl extends AbstractRepositoryImpl<Author> implements AuthorRepository {

    AuthorRepositoryImpl() {
        super(Author.class);
    }

    @Override
    public Author getByName(String name) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        final Root<Author> rows = query.from(Author.class);
        query.where(criteriaBuilder.equal(rows.get(Author_.firstName), name));
        return entityManager.createQuery(query).getSingleResult();
    }
}
