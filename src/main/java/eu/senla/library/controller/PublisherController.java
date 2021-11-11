package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.PublisherService;
import eu.senla.library.dto.PublisherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    private final ObjectMapper mapper;

    public String create(String requestJson) {
        try {
            PublisherDto publisherDto = mapper.readValue(requestJson, PublisherDto.class);
            PublisherDto response = publisherService.create(publisherDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            PublisherDto publisherDto = publisherService.getById(id);
            return mapper.writeValueAsString(publisherDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<PublisherDto> publishers = publisherService.getAll();
            return mapper.writeValueAsString(publishers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            PublisherDto publisherDto = mapper.readValue(requestJson, PublisherDto.class);
            PublisherDto response = publisherService.update(publisherDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        publisherService.deleteById(id);
    }
}
