package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "with-users",
        attributeNodes = @NamedAttributeNode("users")
)
public class Role extends BaseEntity {
    @Column(name = "name_role")
    private String nameRole;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();
}
