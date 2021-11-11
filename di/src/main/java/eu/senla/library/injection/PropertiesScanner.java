package eu.senla.library.injection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertiesScanner {

    private final String PROPERTY_EXTENSION = ".properties";
    private final Map<String, String> fieldValueMap;

    public PropertiesScanner() {
        fieldValueMap = new HashMap<>();
    }

    public Map<String, String> scanProject(Class<?> startClass) {
        String directory = getDirectory(startClass);

        try {
            scanDirectory(new File(directory), "");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }

        return fieldValueMap;
    }

    private String getDirectory(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    private void scanDirectory(File file, String packageName) throws ClassNotFoundException, IOException {
        for (File innerFile : Objects.requireNonNull(file.listFiles())) {
            StringBuilder packageBuilder = new StringBuilder(packageName);
            if (innerFile.isDirectory()) {
                packageBuilder.append(innerFile.getName()).append(".");
                scanDirectory(innerFile, packageBuilder.toString());
            }

            String className = packageBuilder.toString() +
                    innerFile.getName().replace(PROPERTY_EXTENSION, "");

            if (!innerFile.getName().endsWith(PROPERTY_EXTENSION)) {
                continue;
            }

            Map<String, String> tempMap = new HashMap<>();
            Properties properties = new Properties();

            try (InputStream stream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(innerFile.getName())) {
                properties.load(stream);
            }

            for (final String name : properties.stringPropertyNames())
                tempMap.put(name, properties.getProperty(name));

            fieldValueMap.putAll(tempMap);
        }
    }
}