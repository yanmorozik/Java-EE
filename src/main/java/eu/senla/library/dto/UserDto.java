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
public class UserDto {

    private Long id;

    private String firstName;

    private String surname;

    private String telephone;

    private CredentialDto credential;

    private List<RoleDto> roles;
}
