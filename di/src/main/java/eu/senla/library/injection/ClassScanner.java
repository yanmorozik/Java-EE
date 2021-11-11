package eu.senla.library.injection;

import eu.senla.library.injection.annotation.Component;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClassScanner {

    private final String CLASS_EXTENSION = ".class";
    private final Set<Class<?>> foundClasses;


    public ClassScanner() {
        this.foundClasses = new HashSet<>();
    }

    public Set<Class<?>> findClasses(Class<?> startClass) {
        String directory = getDirectory(startClass);

        try {
            scanDirectory(new File(directory), "");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        return foundClasses;
    }

    private String getDirectory(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    private void scanDirectory(File file, String packageName) throws ClassNotFoundException {
        for (File innerFile : Objects.requireNonNull(file.listFiles())) {
            StringBuilder packageBuilder = new StringBuilder(packageName);
            if (innerFile.isDirectory()) {
                packageBuilder.append(innerFile.getName()).append(".");
                scanDirectory(innerFile, packageBuilder.toString());
            }

            String className = packageBuilder.toString() +
                    innerFile.getName().replace(CLASS_EXTENSION, "");

            if (!innerFile.getName().endsWith(CLASS_EXTENSION)){
                continue;
            }

            Class<?> clazz = Class.forName(className);

            if (!clazz.isAnnotationPresent(Component.class)){
                continue;
            }

            foundClasses.add(clazz);
        }
    }

}
