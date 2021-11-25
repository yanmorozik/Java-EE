package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
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
