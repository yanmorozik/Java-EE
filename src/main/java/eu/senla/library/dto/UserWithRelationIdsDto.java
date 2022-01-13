package eu.senla.library.dto;

import eu.senla.library.model.Booking;
import eu.senla.library.model.Credential;
import eu.senla.library.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRelationIdsDto {

    private Long id;

    private String surname;

    private String telephone;

    private Long credentialId;

    private List<Long> roleIds;
}
