package eu.senla.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends BaseEntity{
    @Column
    private String nameGenre;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;
}
