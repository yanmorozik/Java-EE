package eu.senla.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column(name="name_role")
    private String nameRole;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    private List<User> users;
}
