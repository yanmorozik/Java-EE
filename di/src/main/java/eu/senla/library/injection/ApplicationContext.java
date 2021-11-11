package eu.senla.library.injection;

import eu.senla.library.injection.annotation.Autowired;
import eu.senla.library.injection.annotation.Component;
import eu.senla.library.injection.annotation.Value;
import eu.senla.library.injection.exception.InjectionException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {

    private final Map<Class<?>, Object> context;
    private final Map<Class<?>, Class<?>> classInterfaceMap;
    private ObjectFactory factory;

    public ApplicationContext() {
        this.context = new HashMap<>();
        this.classInterfaceMap = new HashMap<>();
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public void creatContext(Set<Class<?>> classes,Map<String,String> properties) throws IllegalAccessException {
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(Component.class)) {
                continue;
            }

            Class<?>[] interfaces = clazz.getInterfaces();

            if (interfaces.length == 0) {
                classInterfaceMap.put(clazz, clazz);
                continue;
            }

            for (Class<?> interfaceKey : interfaces) {
                if (!classInterfaceMap.containsValue(interfaceKey)) {
                    classInterfaceMap.put(clazz, interfaceKey);
                }
            }
        }
        fillContext(properties);
    }

    private void fillContext(Map<String,String> properties) throws IllegalAccessException {
        for (Class<?> clazz : classInterfaceMap.keySet()) {
            Object bean = factory.createBean(clazz);
            context.put(clazz, bean);
        }

        for (Map.Entry<Class<?>, Object> entry : context.entrySet()) {
            injectDependencies(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Class<?>, Object> entry : context.entrySet()) {
            injectValues(entry.getKey(), entry.getValue(), properties);
        }
    }

    private void injectValues(Class<?> clazz, Object bean, Map<String, String> properties) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Value.class)) {
                continue;
            }

            String value = field.getAnnotation(Value.class).value();
            field.setAccessible(true);
            field.set(bean, properties.get(value));
        }
    }

    private void injectDependencies(Class<?> clazz, Object bean) throws IllegalAccessException {
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Object instance = this.getBean(field.getType());
            field.setAccessible(true);

            field.set(bean, instance);
        }
    }

    public <T> T getBean(Class<T> type) {
        Set<Map.Entry<Class<?>, Class<?>>> classSet = classInterfaceMap.entrySet().stream()
                .filter(entry -> type.equals(entry.getValue()))
                .collect(Collectors.toSet());
        if (classSet.size() != 1) {
            throw new InjectionException("Message");
        }

        Class<?> clazz = classSet.stream()
                .findFirst()
                .get()
                .getKey();

        if (context.containsKey(clazz)) {
            return (T) context.get(clazz);
        }

        Object bean = factory.createBean(clazz);
        context.put(clazz, bean);
        return (T) bean;
    }
}
