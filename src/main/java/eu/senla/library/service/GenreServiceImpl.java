package eu.senla.library.service;

import eu.senla.library.api.repository.GenreRepository;
import eu.senla.library.api.service.GenreService;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.model.Genre;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public GenreDto create(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        Genre response = genreRepository.add(genre);
        return modelMapper.map(response, GenreDto.class);
    }

    @Override
    public GenreDto getById(Long id) {
        Genre response = genreRepository.findById(id);
        return modelMapper.map(response, GenreDto.class);
    }

    @Override
    public List<GenreDto> getAll() {

        List<Genre> genres = genreRepository.findAll();
        return modelMapper.map(genres, new TypeToken<List<GenreDto>>() {
        }.getType());
    }

    @Override
    public GenreDto update(GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        Genre response = genreRepository.update(genre);
        return modelMapper.map(response, GenreDto.class);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

}
