package eu.senla.library.controller;

import eu.senla.library.api.service.GenreService;
import eu.senla.library.dto.ErrorMessageDto;
import eu.senla.library.dto.GenreDto;
import eu.senla.library.exception.GenreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("genres")
public class GenreController{

    private final GenreService genreService;

    @ExceptionHandler(GenreNotFoundException.class)
    public ErrorMessageDto catchException(){
        return new ErrorMessageDto("error");
    }

    @PostMapping
    public GenreDto create(@RequestBody GenreDto genreDto) {
        return genreService.create(genreDto);
    }

    @GetMapping("/{id}")
    public GenreDto getById(@PathVariable Long id) throws GenreNotFoundException {
        return genreService.getById(id);
    }

    @GetMapping
    public List<GenreDto> getAll() {
        return genreService.getAll();
    }

    @PutMapping
    public GenreDto update(@RequestBody GenreDto genreDto) {
        return genreService.update(genreDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
    }

}
