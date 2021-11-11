package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.GenreService;
import eu.senla.library.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreController{

    private final GenreService genreService;

    private final ObjectMapper mapper;

    public String create(String requestJson) {
        try {
            GenreDto genreDto = mapper.readValue(requestJson, GenreDto.class);
            GenreDto response = genreService.create(genreDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            GenreDto genreDto = genreService.getById(id);
            return mapper.writeValueAsString(genreDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<GenreDto> genres = genreService.getAll();
            return mapper.writeValueAsString(genres);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            GenreDto genreDto = mapper.readValue(requestJson, GenreDto.class);
            GenreDto response = genreService.update(genreDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        genreService.deleteById(id);
    }
}
