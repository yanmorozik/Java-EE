package eu.senla.library;

import eu.senla.library.config.ContextConfiguration;
import eu.senla.library.config.DatabaseConfiguration;
import eu.senla.library.controller.RoleController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class, DatabaseConfiguration.class);

        RoleController roleController = context.getBean(RoleController.class);
        System.out.println(roleController.getUserRoleWithUserJPQL(1L));
        System.out.println(roleController.getUserRoleWithUserCriteria(1L));
        System.out.println(roleController.getUserRoleWithUserGraph(1L));
    }
}





