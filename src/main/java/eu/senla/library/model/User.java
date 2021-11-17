package eu.senla.library.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User extends BaseEntity{

    private String firstName;

    private String surname;

    private String telephone;

    private String login;

    private String password;

    private String passwordConfirm;

    private List<Booking> bookings;

    private List<Role> roles;
}

