package eu.senla.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends BaseEntity {
    @Column(name = "name_genre")
    private String nameGenre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private List<Book> books;
}
