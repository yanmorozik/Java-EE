package eu.senla.library.controller;

import eu.senla.library.api.service.ServiceInterface;
import eu.senla.library.injection.annotation.Autowired;
import eu.senla.library.injection.annotation.Component;

@Component
public class Controller {
    @Autowired
    private ServiceInterface service;

    public String print() {
        return service.print();
    }
}
