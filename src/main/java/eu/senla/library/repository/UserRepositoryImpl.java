package eu.senla.library.repository;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public User add(User User) {
        users.add(User);
        return User;
    }

    @Override
    public User findById(Long id) {
        return users.get(id.intValue());
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User update(User user) {
        Long index = user.getId();
        users.set(index.intValue(),user);
        return user;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id.intValue());
    }
}
