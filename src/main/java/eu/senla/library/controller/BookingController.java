package eu.senla.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.library.api.service.BookingService;
import eu.senla.library.dto.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final ObjectMapper mapper;

    BookingDto bookingDto;

    public String create(String requestJson) {
        try {
            bookingDto = mapper.readValue(requestJson, BookingDto.class);
            BookingDto response = bookingService.create(bookingDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getById(Long id) {
        try {
            bookingDto = bookingService.getById(id);
            return mapper.writeValueAsString(bookingDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getAll() {
        try {
            List<BookingDto> bookings = bookingService.getAll();
            return mapper.writeValueAsString(bookings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson) {
        try {
            bookingDto = mapper.readValue(requestJson, BookingDto.class);
            BookingDto response = bookingService.update(bookingDto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(BookingDto bookDto) {
        bookingService.delete(bookDto);
    }

    public void deleteById(Long id) {
        bookingService.deleteById(id);
    }
}
