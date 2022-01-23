package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@ToString
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "authorEntityGraph",
        attributeNodes = {@NamedAttributeNode(value = "books")})
public class Author extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column
    private String surname;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();
}
