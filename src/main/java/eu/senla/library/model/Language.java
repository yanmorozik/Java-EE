package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Language extends BaseEntity{

    private String nameLanguage;

    private List<Book> books;
}
