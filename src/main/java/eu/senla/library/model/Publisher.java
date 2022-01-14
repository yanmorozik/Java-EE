package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "publishers")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "publisherEntityGraph",
        attributeNodes = {@NamedAttributeNode(value = "books")})
public class Publisher extends BaseEntity {
    @Column(name = "name_publisher")
    private String namePublisher;

    @Column
    private String telephone;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "publishers",cascade = CascadeType.ALL)
    private Set<Book> books= new HashSet<>();
}
