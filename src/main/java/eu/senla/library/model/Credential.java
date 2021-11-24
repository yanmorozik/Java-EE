package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "credentials")
@AllArgsConstructor
@NoArgsConstructor
public class Credential extends BaseEntity{
    @Column
    private String login;

    @Column
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "credential")
    private User user;
}
