package eu.senla.library;

import eu.senla.library.controller.Controller;
import eu.senla.library.injection.ApplicationContext;
import eu.senla.library.injection.DependencyInjector;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        DependencyInjector.run(Application.class, context );
        Controller controller = context.getBean(Controller.class);

        System.out.println(controller.print());

    }
}
