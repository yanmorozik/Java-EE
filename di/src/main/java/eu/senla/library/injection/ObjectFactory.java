package eu.senla.library.injection;

import eu.senla.library.injection.exception.InjectionException;

public class ObjectFactory {
    public <T> T createBean(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new InjectionException("Bean creation filed", e);
        }
    }
}
