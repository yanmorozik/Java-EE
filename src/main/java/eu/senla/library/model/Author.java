package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Author extends BaseEntity{

    private String firstName;

    private String surname;

    private List<Book> books;
}
