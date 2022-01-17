package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoFilter {
    private Long id;

    private String name;

    private String description;

    private Integer minNumberOfPage;

    private Integer maxNumberOfPage;

    private Integer minYearOfPublishing;

    private Integer maxYearOfPublishing;

    private Integer minNumberOfCopies;

    private Integer maxNumberOfCopies;

    private AuthorDto author;

    private GenreDto genre;

    private PublisherDto publisher;

    private LanguageDto language;
}
