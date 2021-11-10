package eu.senla.library.injection;

import eu.senla.library.injection.exception.InjectionException;

public class DependencyInjector {
    public static void run(Class<?> startClass, ApplicationContext applicationContext) {
        try {
            ClassScanner scanner = new ClassScanner();
            ObjectFactory factory = new ObjectFactory();
            PropertiesScanner propertiesScanner = new PropertiesScanner();

            applicationContext.setFactory(factory);
            applicationContext.creatContext(scanner.findClasses(startClass),propertiesScanner.scanProject(startClass));
        } catch (IllegalAccessException e) {
            throw new InjectionException("Message ", e);
        }
    }
}
