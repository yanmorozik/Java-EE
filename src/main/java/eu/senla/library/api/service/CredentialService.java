package eu.senla.library.api.service;

import eu.senla.library.dto.CredentialDto;
import eu.senla.library.exception.CredentialNotFoundException;

import java.util.List;

public interface CredentialService {
    CredentialDto create(CredentialDto  credentialDto);

    CredentialDto getById(Long id) throws CredentialNotFoundException;

    List<CredentialDto> getAll();

    CredentialDto update(CredentialDto credentialDto);

    void deleteById(Long id);
}