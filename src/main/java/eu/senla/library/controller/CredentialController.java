package eu.senla.library.controller;

import eu.senla.library.api.service.CredentialService;
import eu.senla.library.dto.CredentialDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Credential;
import liquibase.pro.packaged.V;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("credentials")
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    public ResponseEntity<CredentialDto> create(@RequestBody CredentialDto credentialDto) {
        CredentialDto dto = credentialService.create(credentialDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialDto> getById(@PathVariable Long id) throws NotFoundException {
        CredentialDto dto = credentialService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CredentialDto>> getAll() {
        List<CredentialDto> credentials= credentialService.getAll();
        return ResponseEntity.ok(credentials);
    }

    @PutMapping
    public ResponseEntity<CredentialDto> update(@RequestBody CredentialDto credentialDto) {
        CredentialDto dto = credentialService.update(credentialDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        credentialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
