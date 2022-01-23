package eu.senla.library.controller;

import eu.senla.library.api.service.CredentialService;
import eu.senla.library.dto.CredentialDto;
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
@RequestMapping("credentials")
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialDto create(@RequestBody CredentialDto credentialDto) {
        return credentialService.create(credentialDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialDto getById(@PathVariable Long id) throws NotFoundException {
        return credentialService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CredentialDto> getAll(@RequestParam(defaultValue = "1") int start,
                                                      @RequestParam(defaultValue = "3") int max) {
        return credentialService.getAll(start,max);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialDto update(@RequestBody CredentialDto credentialDto) {
        return credentialService.update(credentialDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        credentialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
