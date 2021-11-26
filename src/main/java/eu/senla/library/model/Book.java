package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
    @Column(name = "name_book")
    private String nameBook;

    @Column
    private String description;

    @Column(name = "number_of_page")
    private Integer numberOfPage;

    @Column(name = "year_of_publishing")
    private Integer yearOfPublishing;

    @Column(name = "number_of_copies")
    private Integer numberOfCopies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "book_publisher",
            joinColumns = {@JoinColumn(name = "publisher_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Publisher> publishers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Booking> bookings;
}
