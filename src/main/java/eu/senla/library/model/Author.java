package eu.senla.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity{
    @Column
    private String firstName;

    @Column
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
