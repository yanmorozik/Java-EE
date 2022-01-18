package eu.senla.library.converter;

import eu.senla.library.dto.CredentialDto;
import eu.senla.library.model.Credential;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CredentialConverter extends Converter<Credential, CredentialDto> {
    public CredentialConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
