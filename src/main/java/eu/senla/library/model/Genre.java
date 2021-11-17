package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Genre extends BaseEntity{

    private String nameGenre;

    private List<Book> books;
}
