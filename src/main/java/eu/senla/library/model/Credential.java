package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    @Transient
    private String passwordConfirm;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
    private User user;
}
