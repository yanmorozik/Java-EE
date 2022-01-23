package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "credentials")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "credentialEntityGraph",
        includeAllAttributes = true)
public class Credential extends BaseEntity {
    @Column
    private String login;

    @Column
    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
    private User user;
}
