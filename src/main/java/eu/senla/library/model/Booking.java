package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.GregorianCalendar;

@Getter
@Setter
@ToString
public class Booking  {

    private Long id;

    private GregorianCalendar startTime;

    private GregorianCalendar endTime;

    private Book book;

    private User user;
}
