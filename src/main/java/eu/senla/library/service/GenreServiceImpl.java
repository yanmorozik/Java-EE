package eu.senla.library.service;

import eu.senla.library.api.repository.GenreRepository;
import eu.senla.library.api.service.GenreService;
import eu.senla.library.converter.GenreConverter;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreConverter genreConverter;

    @Transactional
    @Override
    public GenreDto create(GenreDto genreDto) {
        final Genre genre = genreConverter.convert(genreDto);
        final Genre response = genreRepository.add(genre);
        return genreConverter.convert(response);
    }

    @Transactional
    @Override
    public GenreDto getById(Long id) throws NotFoundException {
        Genre response = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return genreConverter.convert(response);
    }

    @Transactional
    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.findAll();
        return genreConverter.convert(genres);
    }

    @Transactional
    @Override
    public GenreDto update(GenreDto genreDto) {
        final Genre genre = genreConverter.convert(genreDto);
        final Genre response = genreRepository.update(genre);
        return genreConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
