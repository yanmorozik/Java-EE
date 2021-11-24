package eu.senla.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="publishers")
@AllArgsConstructor
@NoArgsConstructor
public class Publisher extends BaseEntity{
    @Column(name="name_publisher")
    private String namePublisher;

    @Column
    private String telephone;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "publishers")
    private List<Book> books;
}
