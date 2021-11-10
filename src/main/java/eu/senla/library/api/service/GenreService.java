package eu.senla.library.api.service;

import eu.senla.library.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto create(GenreDto  genreDto);

    GenreDto getById(Long id);

    List<GenreDto> getAll();

    GenreDto update(GenreDto genreDto);

    void delete(GenreDto genreDto);

    void deleteById(Long id);
}
