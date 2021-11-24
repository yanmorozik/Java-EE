package eu.senla.library.repository;

import eu.senla.library.api.repository.GenreRepository;
import eu.senla.library.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepositoryImpl extends AbstractRepositoryImpl<Genre> implements GenreRepository {

    public GenreRepositoryImpl() {
        super(Genre.class);
    }
}
