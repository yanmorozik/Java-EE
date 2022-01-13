package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookWithRelationIdsDto {

    private Long id;

    private String name;

    private String description;

    private Integer numberOfPage;

    private Integer yearOfPublishing;

    private Integer numberOfCopies;

    private List<Long> authorIds;

    private Long genreId;

    private List<Long> publisherIds;

    private Long languageId;

}
