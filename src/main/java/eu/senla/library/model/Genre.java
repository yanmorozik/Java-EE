package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books= new HashSet<>();
}
