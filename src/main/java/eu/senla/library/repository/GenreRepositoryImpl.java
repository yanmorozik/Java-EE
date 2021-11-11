package eu.senla.library.repository;

import eu.senla.library.api.repository.GenreRepository;
import eu.senla.library.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private final List<Genre> genres = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }

    @Override
    public Genre add(Genre genre) {
        genre.setId(generateFacilitiesId());
        genres.add(genre);
        return genre;
    }

    @Override
    public Genre findById(Long id) {
        return genres.get(id.intValue());
    }

    @Override
    public List<Genre> findAll() {
        return genres;
    }

    @Override
    public Genre update(Genre genre) {
        Long index = genre.getId();
        genres.set(index.intValue(), genre);
        return genre;
    }

    @Override
    public void delete(Genre genre) {
        genres.remove(genre);
    }

    @Override
    public void deleteById(Long id) {
        genres.remove(id.intValue());
    }
}
