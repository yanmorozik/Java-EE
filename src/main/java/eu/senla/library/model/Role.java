package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Role extends BaseEntity{

    private String nameRole;

    private List<User> users;
}
