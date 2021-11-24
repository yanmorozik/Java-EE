package eu.senla.library.repository;

import eu.senla.library.api.repository.PublisherRepository;
import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.model.Publisher;
import eu.senla.library.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }
}
