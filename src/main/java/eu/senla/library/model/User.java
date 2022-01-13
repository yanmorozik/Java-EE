package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "userEntityGraph",
        includeAllAttributes = true)
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column
    private String surname;

    @Column
    private String telephone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE/*, orphanRemoval = true*/)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private Credential credential;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();
}

