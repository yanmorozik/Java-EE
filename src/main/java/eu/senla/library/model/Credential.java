package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "credentials")
@AllArgsConstructor
@NoArgsConstructor
public class Credential extends BaseEntity {
    @Column
    private String login;

    @Column
    private String password;

//    @Column
//    private String passwordConfirm;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "credential",cascade = CascadeType.REMOVE)
    private User user;
}
