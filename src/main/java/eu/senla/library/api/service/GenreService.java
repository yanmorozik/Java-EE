package eu.senla.library.api.service;

import eu.senla.library.dto.GenreDto;
import eu.senla.library.exception.GenreNotFoundException;

import java.util.List;

public interface GenreService {

    GenreDto create(GenreDto  genreDto);

    GenreDto getById(Long id) throws GenreNotFoundException;

    List<GenreDto> getAll();

    GenreDto update(GenreDto genreDto);

    void deleteById(Long id);
}
