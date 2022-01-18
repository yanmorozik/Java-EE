package eu.senla.library.api.service;

import eu.senla.library.dto.CredentialDto;
import eu.senla.library.exception.NotFoundException;

import java.util.List;

public interface CredentialService {
    CredentialDto create(CredentialDto credentialDto);

    CredentialDto getById(Long id) throws NotFoundException;

    List<CredentialDto> getAll(int start, int max);

    CredentialDto update(CredentialDto credentialDto);

    void deleteById(Long id);
}
