package eu.senla.library.injection;

import eu.senla.library.injection.exception.InjectionException;

public class DependencyInjector {
    public static void run(Class<?> startClass, ApplicationContext applicationContext) {
        try {
            ClassScanner scanner = new ClassScanner();
            ObjectFactory factory = new ObjectFactory();
            applicationContext.setFactory(factory);
            applicationContext.creatContext(scanner.findClasses(startClass));
        } catch (IllegalAccessException e) {
            throw new InjectionException("Message ", e);
        }
    }
}
