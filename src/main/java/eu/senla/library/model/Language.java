package eu.senla.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name="languages")
@AllArgsConstructor
@NoArgsConstructor
public class Language extends BaseEntity{
    @Column
    private String nameLanguage;

    @OneToMany(mappedBy = "language")
    private List<Book> books;
}
