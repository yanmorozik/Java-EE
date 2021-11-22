package eu.senla.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column
    private String nameRole;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
