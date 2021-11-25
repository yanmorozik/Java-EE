package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.AuthorService;
import eu.senla.library.dto.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(
            AuthorController.class);

    private final AuthorService authorService;

    private final ObjectMapper mapper;

    public String create(String requestJson) {
        try {
            AuthorDto authorDto = mapper.readValue(requestJson, AuthorDto.class);
            AuthorDto response = authorService.create(authorDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            AuthorDto authorDto = authorService.getById(id);
            return mapper.writeValueAsString(authorDto);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<AuthorDto> authors = authorService.getAll();
            return mapper.writeValueAsString(authors);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            AuthorDto authorDto = mapper.readValue(requestJson, AuthorDto.class);
            AuthorDto response = authorService.update(authorDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        authorService.deleteById(id);
    }


}
