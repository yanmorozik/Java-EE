package eu.senla.library.converter;

import eu.senla.library.dto.AuthorDto;
import eu.senla.library.model.Author;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorConverter {

    private final ModelMapper modelMapper;

    public Author convert(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }

    public AuthorDto convert(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    public List<AuthorDto> convert(List<Author> authors) {
        return modelMapper.map(authors, new TypeToken<List<AuthorDto>>() {
        }.getType());
    }
}
