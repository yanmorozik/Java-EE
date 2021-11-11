package eu.senla.library.api.repository;

import eu.senla.library.model.Genre;

import java.util.List;

public interface GenreRepository {

    Genre add(Genre genre);

    Genre findById(Long id);

    List<Genre> findAll();

    Genre update(Genre genre);

    void delete(Genre book);

    void deleteById(Long id);
}
