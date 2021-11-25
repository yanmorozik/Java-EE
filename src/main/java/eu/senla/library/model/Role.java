package eu.senla.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.NamedAttributeNode;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name="with-users",
        attributeNodes = @NamedAttributeNode("users")
)
public class Role extends BaseEntity{
    @Column(name="name_role")
    private String nameRole;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    private List<User> users;
}
