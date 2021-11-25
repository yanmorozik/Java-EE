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
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
public class Language extends BaseEntity {
    @Column(name = "name_language")
    private String nameLanguage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
    private List<Book> books;
}
