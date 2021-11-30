package eu.senla.library.converter;

import eu.senla.library.dto.GenreDto;
import eu.senla.library.model.Genre;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreConverter {
    private final ModelMapper modelMapper;

    public Genre convert(GenreDto genreDto) {
        return modelMapper.map(genreDto, Genre.class);
    }

    public GenreDto convert(Genre genre) {
        return modelMapper.map(genre, GenreDto.class);
    }

    public List<GenreDto> convert(List<Genre> genres) {
        return modelMapper.map(genres, new TypeToken<List<GenreDto>>() {
        }.getType());
    }
}
