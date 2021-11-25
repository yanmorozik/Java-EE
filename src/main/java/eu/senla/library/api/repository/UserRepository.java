package eu.senla.library.api.repository;

import eu.senla.library.model.User;

import java.util.List;

public interface UserRepository extends AbstractRepository<User>{

    User add(User user);

    User findById(Long id);

    List<User> findAll();

    User update(User user);

    void delete(User user);

    void deleteById(Long id);
}
