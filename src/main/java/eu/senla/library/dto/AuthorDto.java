package eu.senla.library.dto;

import eu.senla.library.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto extends BaseEntity {

    private Long id;

    private String firstName;

    private String surname;

}
