package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "bookEntityGraph",
        includeAllAttributes = true
        /* attributeNodes = {
                 @NamedAttributeNode(value = "genre"),
                 @NamedAttributeNode(value = "language"),
                 @NamedAttributeNode(value = "authors"),
                 @NamedAttributeNode(value = "publishers"),
                 @NamedAttributeNode(value = "bookings"),
         }, subgraphs = {
        @NamedSubgraph(
                name = "authorsSubgraph",
                attributeNodes = {
                        @NamedAttributeNode("authors")
                }
        ),
        @NamedSubgraph(
                name = "publishersSubgraph",
                attributeNodes = {
                        @NamedAttributeNode("publishers")
                }
        ),
        @NamedSubgraph(
                name = "bookingsSubgraph",
                attributeNodes = {
                        @NamedAttributeNode("bookings")
                }
        )
}*/)
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
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_publisher",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")}
    )
    private Set<Publisher> publishers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();
}
