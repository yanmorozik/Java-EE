package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.LanguageService;
import eu.senla.library.dto.LanguageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanguageController {

    private static final Logger logger = LoggerFactory.getLogger(
            LanguageController.class);

    private final LanguageService languageService;

    private final ObjectMapper mapper;

    public String create(String requestJson) {
        try {
            LanguageDto languageDto = mapper.readValue(requestJson, LanguageDto.class);
            LanguageDto response = languageService.create(languageDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            LanguageDto languageDto = languageService.getById(id);
            return mapper.writeValueAsString(languageDto);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<LanguageDto> languages = languageService.getAll();
            return mapper.writeValueAsString(languages);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            LanguageDto languageDto = mapper.readValue(requestJson, LanguageDto.class);
            LanguageDto response = languageService.update(languageDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        languageService.deleteById(id);
    }

}
