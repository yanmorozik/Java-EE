package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDto {

    private String login;

    private String password;

    private String passwordConfirm;

}
