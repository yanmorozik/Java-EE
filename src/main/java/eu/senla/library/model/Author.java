package eu.senla.library.model;

import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column
    private String surname;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private List<Book> books;
}
