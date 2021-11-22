package eu.senla.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity{
    @Column
    private String nameBook;

    @Column
    private String description;

    @Column
    private Integer numberOfPage;

    @Column
    private Integer yearOfPublishing;

    @Column
    private Integer numberOfCopies;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "author_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    private List<Author> authors;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "book_publisher",
            joinColumns = { @JoinColumn(name = "publisher_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    private List<Publisher> publishers;

    @OneToMany(mappedBy = "book")
    private List<Booking> bookings;
}
