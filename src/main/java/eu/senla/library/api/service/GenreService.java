package eu.senla.library.api.service;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface GenreService {

    GenreDto create(GenreDto  genreDto);

    GenreDto getById(Long id) throws NotFoundException;

    List<GenreDto> getAll(int start,int max);

    List<GenreDto> getAll();

    GenreDto update(GenreDto genreDto);

    void deleteById(Long id);

    List<GenreDto> getByFiler(String nameGenre);
}
