package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long id;

    private GregorianCalendar startTime;

    private GregorianCalendar endTime;

    private BookWithBookingDto book;

    private UserWithBookingDto user;
}
