package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BookWithBookingDto book;

    private UserWithBookingDto user;
}
