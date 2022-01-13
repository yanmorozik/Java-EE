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
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "languageEntityGraph",
        attributeNodes = {@NamedAttributeNode(value = "books")})
public class Language extends BaseEntity {
    @Column(name = "name_language")
    private String nameLanguage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language",orphanRemoval = true)
    private Set<Book> books= new HashSet<>();
}
