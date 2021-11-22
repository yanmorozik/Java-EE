package eu.senla.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    @Column
    private String firstName;

    @Column
    private String surname;

    @Column
    private String telephone;

    @Column
    private String login;

    @Column
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<Role> roles;
}

