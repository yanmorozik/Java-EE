package eu.senla.library.service;

import eu.senla.library.api.repository.RepositoryInterface;
import eu.senla.library.api.service.ServiceInterface;
import eu.senla.library.injection.annotation.Autowired;
import eu.senla.library.injection.annotation.Component;

@Component
public class ServiceImpl implements ServiceInterface {

    @Autowired
    private RepositoryInterface repository;

    @Override
    public String print() {
        return repository.print();
    }

}
