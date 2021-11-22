package eu.senla.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name="publishers")
@AllArgsConstructor
@NoArgsConstructor
public class Publisher extends BaseEntity{
    @Column
    private String namePublisher;

    @Column
    private String telephone;

    @ManyToMany(mappedBy = "publishers")
    private List<Book> books;
}
