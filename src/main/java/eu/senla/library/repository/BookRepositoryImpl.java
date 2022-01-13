package eu.senla.library.repository;

import eu.senla.library.api.repository.BookRepository;
import eu.senla.library.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookRepositoryImpl extends AbstractRepositoryImpl<Book> implements BookRepository {

    public BookRepositoryImpl() {
        super(Book.class);
    }

    @Override
    protected String getNameGraph() {
        return "bookEntityGraph";
    }

}
