package eu.senla.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="languages")
@AllArgsConstructor
@NoArgsConstructor
public class Language extends BaseEntity{
    @Column(name="name_language")
    private String nameLanguage;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "language")
    private List<Book> books;
}
