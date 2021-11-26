package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "publishers")
@AllArgsConstructor
@NoArgsConstructor
public class Publisher extends BaseEntity {
    @Column(name = "name_publisher")
    private String namePublisher;

    @Column
    private String telephone;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "publishers")
    private List<Book> books;
}
