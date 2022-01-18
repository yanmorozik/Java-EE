package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRelationIdsDto {

    private Long id;

    private String firstName;

    private String surname;

    private String telephone;

    private CredentialDto credential;

    private List<Long> roleIds;
}
