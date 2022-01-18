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

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    @Override
    public GenreDto getById(Long id) throws NotFoundException {
        Genre response = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return genreConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> getAll(int start,int max) {
        List<Genre> genres = genreRepository.findAll(start,max);
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

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> getByFiler(String nameGenre,int start, int max) {

        GenreDto filter = GenreDto.builder().nameGenre(nameGenre).build();
        List<Genre> genres = genreRepository.findAll(start,max);
        List<GenreDto> genresProtocols = genreConverter.convert(genres);
        List<Function<GenreDto, String>> comparingFields = Collections.singletonList(GenreDto::getNameGenre);
        return filter(genresProtocols, filter, comparingFields);

    }

    private List<GenreDto> filter(List<GenreDto> allProtocols, GenreDto filter,
                                         List<Function<GenreDto, String>> comparingFields) {
        return allProtocols.stream()
                .filter(protocol -> test(protocol, filter, comparingFields))
                .collect(Collectors.toList());
    }

    private boolean test(GenreDto protocol, GenreDto filter,
                                List<Function<GenreDto, String>> comparingFields) {
        return comparingFields.stream()
                .allMatch(func -> func.apply(protocol).contains(func.apply(filter)));
    }
}
