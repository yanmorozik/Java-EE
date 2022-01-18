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
    public ResponseEntity<PublisherDto> create(@RequestBody PublisherDto publisherDto) {
        PublisherDto dto = publisherService.create(publisherDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PublisherDto> getById(@PathVariable Long id) throws NotFoundException {
        PublisherDto dto = publisherService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<PublisherDto>> getAll(@RequestParam(defaultValue = "1") int start,
                                                     @RequestParam(defaultValue = "3") int max) {
        List<PublisherDto> publishers = publisherService.getAll(start, max);
        return ResponseEntity.ok(publishers);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PublisherDto> update(@RequestBody PublisherDto publisherDto) {
        PublisherDto dto = publisherService.update(publisherDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtration")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<PublisherDto>> getByFiler(@RequestParam(defaultValue = "") String namePublisher,
                                                         @RequestParam(defaultValue = "") String telephone,
                                                         @RequestParam(defaultValue = "1") int start,
                                                         @RequestParam(defaultValue = "3") int max) {
        List<PublisherDto> publishers = publisherService.getByFiler(namePublisher, telephone, start, max);
        return ResponseEntity.ok(publishers);
    }
}
