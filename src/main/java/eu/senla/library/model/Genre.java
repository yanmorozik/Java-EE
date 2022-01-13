package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "genreEntityGraph",
        attributeNodes = {@NamedAttributeNode(value = "books")})
public class Genre extends BaseEntity {
    @Column(name = "name_genre")
    private String nameGenre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre", orphanRemoval = true)
    private Set<Book> books= new HashSet<>();
}
