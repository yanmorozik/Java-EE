package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String name;

    private String description;

    private Integer numberOfPage;

    private Integer yearOfPublishing;

    private Integer numberOfCopies;

    private List<AuthorDto> authors;

    private GenreDto genre;

    private List<PublisherDto> publishers;

    private LanguageDto language;

}
