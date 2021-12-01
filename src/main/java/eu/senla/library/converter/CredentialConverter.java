package eu.senla.library.converter;

import eu.senla.library.dto.CredentialDto;
import eu.senla.library.model.Credential;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CredentialConverter {
    private final ModelMapper modelMapper;

    public Credential convert(CredentialDto credentialDto) {
        return modelMapper.map(credentialDto, Credential.class);
    }

    public CredentialDto convert(Credential credential) {
        return modelMapper.map(credential, CredentialDto.class);
    }

    public List<CredentialDto> convert(List<Credential> credentials) {
        return modelMapper.map(credentials, new TypeToken<List<CredentialDto>>() {
        }.getType());
    }

}
