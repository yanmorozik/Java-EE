package eu.senla.library.controller;

import eu.senla.library.api.service.CredentialService;
import eu.senla.library.dto.CredentialDto;
import eu.senla.library.dto.ErrorMessageDto;
import eu.senla.library.exception.CredentialNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("credentials")
public class CredentialController {

    private final CredentialService credentialService;

    @ExceptionHandler(CredentialNotFoundException.class)
    public ErrorMessageDto catchException(){
        return new ErrorMessageDto("error");
    }

    @PostMapping
    public CredentialDto create(@RequestBody CredentialDto credentialDto) {
        return credentialService.create(credentialDto);
    }

    @GetMapping("/{id}")
    public CredentialDto getById(@PathVariable Long id) throws CredentialNotFoundException {
        return credentialService.getById(id);
    }

    @GetMapping
    public List<CredentialDto> getAll() {
        return credentialService.getAll();
    }

    @PutMapping
    public CredentialDto update(@RequestBody CredentialDto credentialDto) {
        return credentialService.update(credentialDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        credentialService.deleteById(id);
    }

}
