package eu.senla.library.controller;

import eu.senla.library.api.service.PublisherService;
import eu.senla.library.dto.ErrorMessageDto;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.exception.PublisherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @ExceptionHandler(PublisherNotFoundException.class)
    public ErrorMessageDto catchException(){
        return new ErrorMessageDto("error");
    }

    @PostMapping
    public PublisherDto create(@RequestBody PublisherDto publisherDto) {
        return publisherService.create(publisherDto);
    }

    @GetMapping("/{id}")
    public PublisherDto getById(@PathVariable Long id) throws PublisherNotFoundException {
        return publisherService.getById(id);
    }

    @GetMapping
    public List<PublisherDto> getAll() {
        return publisherService.getAll();
    }

    @PutMapping
    public PublisherDto update(@RequestBody PublisherDto publisherDto) {
        return publisherService.update(publisherDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        publisherService.deleteById(id);
    }
}
