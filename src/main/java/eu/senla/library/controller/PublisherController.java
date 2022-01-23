package eu.senla.library.controller;

import eu.senla.library.api.service.PublisherService;
import eu.senla.library.dto.PublisherDto;
import eu.senla.library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PublisherDto create(@RequestBody PublisherDto publisherDto) {
        return publisherService.create(publisherDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PublisherDto getById(@PathVariable Long id) throws NotFoundException {
        return publisherService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<PublisherDto> getAll(@RequestParam(defaultValue = "1") int start,
                                                     @RequestParam(defaultValue = "3") int max) {
        return publisherService.getAll(start, max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PublisherDto update(@RequestBody PublisherDto publisherDto) {
        return publisherService.update(publisherDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<PublisherDto> getByFiler(@RequestParam(defaultValue = "") String namePublisher,
                                                         @RequestParam(defaultValue = "") String telephone,
                                                         @RequestParam(defaultValue = "1") int start,
                                                         @RequestParam(defaultValue = "3") int max) {
        return publisherService.getByFiler(namePublisher, telephone, start, max);
    }
}
