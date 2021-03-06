package eu.senla.library.service;

import eu.senla.library.api.repository.CredentialRepository;
import eu.senla.library.api.service.CredentialService;
import eu.senla.library.converter.CredentialConverter;
import eu.senla.library.dto.CredentialDto;
import eu.senla.library.exception.NotFoundException;
import eu.senla.library.model.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final CredentialConverter credentialConverter;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CredentialDto create(CredentialDto credentialDto) {
        final Credential credential = credentialConverter.convert(credentialDto);
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        final Credential response = credentialRepository.add(credential);
        return credentialConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public CredentialDto getById(Long id) throws NotFoundException {
        Credential response = credentialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return credentialConverter.convert(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CredentialDto> getAll(int start,int max) {
        List<Credential> credentials = credentialRepository.findAll(start,max);
        return credentialConverter.convert(credentials);
    }

    @Transactional
    @Override
    public CredentialDto update(CredentialDto credentialDto) {
        final Credential credential = credentialConverter.convert(credentialDto);
        final Credential response = credentialRepository.update(credential);
        return credentialConverter.convert(response);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        credentialRepository.deleteById(id);
    }

    @Override
    public CredentialDto getByLoginWithRole(String login) {
        Credential credential = credentialRepository.findByLogin(login);
        return credentialConverter.convert(credential);
    }
}
