package eu.senla.library.controller;

import eu.senla.library.api.service.CredentialService;
import eu.senla.library.dto.CredentialDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Credential;
import liquibase.pro.packaged.V;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("credentials")
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CredentialDto> create(@RequestBody CredentialDto credentialDto) {
        CredentialDto dto = credentialService.create(credentialDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CredentialDto> getById(@PathVariable Long id) throws NotFoundException {
        CredentialDto dto = credentialService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<CredentialDto>> getAll(@RequestParam(defaultValue = "1") int start,
                                                      @RequestParam(defaultValue = "3") int max) {
        List<CredentialDto> credentials= credentialService.getAll(start,max);
        return ResponseEntity.ok(credentials);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<CredentialDto>> getAll() {
        List<CredentialDto> credentials= credentialService.getAll();
        return ResponseEntity.ok(credentials);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CredentialDto> update(@RequestBody CredentialDto credentialDto) {
        CredentialDto dto = credentialService.update(credentialDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        credentialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
