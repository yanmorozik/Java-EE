package eu.senla.library.repository;

import eu.senla.library.api.repository.UserRepository;
import eu.senla.library.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }

    @Override
    public User add(User user) {
        user.setId(generateFacilitiesId());
        users.add(user);
        return user;
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
