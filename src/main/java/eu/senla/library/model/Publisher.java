package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Publisher extends BaseEntity{

    private String namePublisher;

    private String telephone;

    private List<Book> books;
}
