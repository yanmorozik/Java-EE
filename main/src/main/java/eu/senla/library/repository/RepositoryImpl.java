package eu.senla.library.repository;

import eu.senla.library.api.repository.RepositoryInterface;
import eu.senla.library.injection.annotation.Component;
import eu.senla.library.injection.annotation.Value;

@Component
public class RepositoryImpl implements RepositoryInterface {

    @Value(value = "my.param.db")
    private String value;

    @Override
    public String print() {
        return "Работа с БД  проведена не успешна";
    }
}
