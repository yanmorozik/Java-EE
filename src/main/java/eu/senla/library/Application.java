package eu.senla.library;

import eu.senla.library.api.service.RoleService;
import eu.senla.library.config.ContextConfiguration;
import eu.senla.library.config.DatabaseConfiguration;
import eu.senla.library.controller.*;
import eu.senla.library.model.Book;

import eu.senla.library.service.RoleServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.criterion.Example;
import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class, DatabaseConfiguration.class);
        RoleController roleController = context.getBean(RoleController.class);


        System.out.println(roleController.getUserRoleWithUserGraph(1L));

    }
}





