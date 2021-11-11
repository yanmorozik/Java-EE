package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.BookService;
import eu.senla.library.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final ObjectMapper mapper;

    public String create(String requestJson) {
        try {
            BookDto bookDto = mapper.readValue(requestJson, BookDto.class); //   преобразуем json в bookDto
            BookDto response = bookService.create(bookDto);
            return mapper.writeValueAsString(response);//   записываем BookDto в String
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            BookDto bookDto = bookService.getById(id);
            return mapper.writeValueAsString(bookDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<BookDto> books = bookService.getAll();
            return mapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            BookDto bookDto = mapper.readValue(requestJson, BookDto.class);
            BookDto response = bookService.update(bookDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        bookService.deleteById(id);
    }
}
