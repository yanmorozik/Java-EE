package eu.senla.library;

import eu.senla.library.config.ContextConfiguration;
import eu.senla.library.controller.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        LanguageController languageController = context.getBean(LanguageController.class);

        languageController.create("{\"nameLanguage\":\"English\"}");
    }
}
