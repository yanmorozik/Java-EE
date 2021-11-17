package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Book extends BaseEntity{

    private String nameBook;

    private String description;

    private Integer numberOfPage;

    private Integer yearOfPublishing;

    private Integer numberOfCopies;

    private Genre genre;

    private Language language;

    private List<Author> authors;

    private List<Publisher> publishers;

    private List<Booking> bookings;
}
