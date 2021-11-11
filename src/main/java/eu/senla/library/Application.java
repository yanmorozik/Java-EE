package eu.senla.library;

import eu.senla.library.config.ContextConfiguration;
import eu.senla.library.controller.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        String StringForRead;

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        BookController bookController = context.getBean(BookController.class);
        AuthorController authorController = context.getBean(AuthorController.class);
        BookingController bookingController = context.getBean(BookingController.class);
        GenreController genreController = context.getBean(GenreController.class);
        LanguageController languageController = context.getBean(LanguageController.class);
        PublisherController publisherController = context.getBean(PublisherController.class);
        RoleController roleController = context.getBean(RoleController.class);
        UserController userController = context.getBean(UserController.class);

        //////////////////////////////////////////// book

        String jsonStringBook1 = "{\"name\":\"some name1\",\"description\":\"description\",\"numberOfPage\":330,\"yearOfPublishing\":2002,\"numberOfCopies\":20,\"authors\":[{\"id\":1,\"firstName\":\"name\",\"surname\":\"surname\"},{\"id\":null,\"firstName\":\"name2\",\"surname\":\"surname2\"}],\"genre\":{\"id\":0,\"nameGenre\":\"detective\"},\"publishers\":[{\"id\":1,\"namePublisher\":\"some name\",\"telephone\":\"+375558336195\"},{\"id\":2,\"namePublisher\":\"some name2\",\"telephone\":\"+375558476196\"}],\"language\":{\"id\":0,\"nameLanguage\":\"English\"}}";
        String jsonStringBook2 = "{\"name\":\"some name2\",\"description\":\"description\",\"numberOfPage\":330,\"yearOfPublishing\":2002,\"numberOfCopies\":20,\"authors\":[{\"id\":1,\"firstName\":\"name\",\"surname\":\"surname\"},{\"id\":null,\"firstName\":\"name2\",\"surname\":\"surname2\"}],\"genre\":{\"id\":0,\"nameGenre\":\"detective\"},\"publishers\":[{\"id\":1,\"namePublisher\":\"some name\",\"telephone\":\"+375558336195\"},{\"id\":2,\"namePublisher\":\"some name2\",\"telephone\":\"+375558476196\"}],\"language\":{\"id\":0,\"nameLanguage\":\"English\"}}";
        String jsonStringBook3 = "{\"id\":0,\"name\":\"some name3\",\"description\":\"description\",\"numberOfPage\":330,\"yearOfPublishing\":2002,\"numberOfCopies\":20,\"authors\":[{\"id\":1,\"firstName\":\"name\",\"surname\":\"surname\"},{\"id\":null,\"firstName\":\"name2\",\"surname\":\"surname2\"}],\"genre\":{\"id\":0,\"nameGenre\":\"detective\"},\"publishers\":[{\"id\":1,\"namePublisher\":\"some name\",\"telephone\":\"+375558336195\"},{\"id\":2,\"namePublisher\":\"some name2\",\"telephone\":\"+375558476196\"}],\"language\":{\"id\":0,\"nameLanguage\":\"English\"}}";

        bookController.create(jsonStringBook1);
        bookController.create(jsonStringBook2); //вставляем 2 записи

        StringForRead = bookController.getById(0L); //читаем одну из них
        System.out.println(StringForRead);

        bookController.deleteById(1L);  //удоляем другую

        bookController.update(jsonStringBook3); //обновляем запись(индекс должен совпадать)

        StringForRead = bookController.getById(0L); //читаем обновлённую запись
        System.out.println(StringForRead);

        //////////////////////////////////////////// author

        String jsonStringAuthor1 = "{\"firstName\":\"Alex\",\"surname\":\"Goncharov\"}";
        String jsonStringAuthor2 = "{\"firstName\":\"Fedya\",\"surname\":\"Pupkin\"}";
        String jsonStringAuthor3 = "{\"id\":0,\"firstName\":\"Vanya\",\"surname\":\"Markovkin\"}";

        authorController.create(jsonStringAuthor1);
        authorController.create(jsonStringAuthor2);

        StringForRead = authorController.getById(0L);
        System.out.println(StringForRead);

        authorController.deleteById(1L);

        authorController.update(jsonStringAuthor3);

        StringForRead = authorController.getById(0L);
        System.out.println(StringForRead);

        //////////////////////////////////////////// booking

        String jsonStringBooking1 = "{\"startTime\":1636494500475,\"endTime\":1636494500475,\"book\":{\"name\":\"luntik\"},\"user\":{\"firstName\":\"noname\",\"surname\":\"noname\"}}";
        String jsonStringBooking2 = "{\"startTime\":1636494500475,\"endTime\":1636494500475,\"book\":{\"name\":\"luntik\"},\"user\":{\"firstName\":\"noname\",\"surname\":\"noname\"}}";
        String jsonStringBooking3 = "{\"id\":0,\"startTime\":1636494500475,\"endTime\":1636494500475,\"book\":{\"name\":\"mur\"},\"user\":{\"firstName\":\"noname\",\"surname\":\"noname\"}}";

        bookingController.create(jsonStringBooking1);
        bookingController.create(jsonStringBooking2);

        StringForRead = bookingController.getById(0L);
        System.out.println(StringForRead);

        bookingController.deleteById(1L);

        bookingController.update(jsonStringBooking3);

        StringForRead = bookingController.getById(0L);
        System.out.println(StringForRead);

        //////////////////////////////////////////// Genre

        String jsonStringGenre1 = "{\"nameGenre\":\"Detective\"}";
        String jsonStringGenre2 = "{\"nameGenre\":\"Detective\"}";
        String jsonStringGenre3 = "{\"id\":0,\"nameGenre\":\"Fantastic\"}";

        genreController.create(jsonStringGenre1);
        genreController.create(jsonStringGenre2);

        StringForRead = genreController.getById(0L);
        System.out.println(StringForRead);

        genreController.deleteById(1L);

        genreController.update(jsonStringGenre3);

        StringForRead = genreController.getById(0L);
        System.out.println(StringForRead);

        //////////////////////////////////////////// Language

        String jsonStringLanguage1 = "{\"nameLanguage\":\"English\"}";
        String jsonStringLanguage2 = "{\"nameLanguage\":\"English\"}";
        String jsonStringLanguage3 = "{\"id\":0,\"nameLanguage\":\"Russian\"}";

        languageController.create(jsonStringLanguage1);
        languageController.create(jsonStringLanguage2);

        StringForRead = languageController.getById(0L);
        System.out.println(StringForRead);

        languageController.deleteById(1L);

        languageController.update(jsonStringLanguage3);

        StringForRead = languageController.getById(0L);
        System.out.println(StringForRead);

        //////////////////////////////////////////// Publisher

        String jsonStringPublisher1 = "{\"namePublisher\":\"some name\",\"telephone\":\"+375443996486\"}";
        String jsonStringPublisher2 = "{\"namePublisher\":\"some name\",\"telephone\":\"+375443996486\"}";
        String jsonStringPublisher3 = "{\"id\":0,\"namePublisher\":\"just name\",\"telephone\":\"+375443996486\"}";

        publisherController.create(jsonStringPublisher1);
        publisherController.create(jsonStringPublisher2);

        StringForRead = publisherController.getById(0L);
        System.out.println(StringForRead);

        publisherController.deleteById(1L);

        publisherController.update(jsonStringPublisher3);

        StringForRead = publisherController.getById(0L);
        System.out.println(StringForRead);

        //////////////////////////////////////////// Role

        String jsonStringRole1 = "{\"nameRole\":\"User\"}";
        String jsonStringRole2 = "{\"nameRole\":\"User\"}";
        String jsonStringRole3 = "{\"id\":0,\"nameRole\":\"Admin\"}";

        roleController.create(jsonStringRole1);
        roleController.create(jsonStringRole2);

        StringForRead = roleController.getById(0L);
        System.out.println(StringForRead);

        roleController.deleteById(1L);

        roleController.update(jsonStringRole3);

        StringForRead = roleController.getById(0L);
        System.out.println(StringForRead);

        //////////////////////////////////////////// User

        String jsomStringUser1 = "{\"firstName\":\"firstname\",\"surname\":\"surname\",\"telephone\":\"+375338569371\",\"login\":\"somelogin\",\"roles\":[{\"id\":1,\"nameRole\":\"User\"},{\"id\":null,\"nameRole\":null}]}";
        String jsomStringUser2 = "{\"firstName\":\"firstname\",\"surname\":\"surname\",\"telephone\":\"+375338569371\",\"login\":\"somelogin\",\"roles\":[{\"id\":1,\"nameRole\":\"User\"},{\"id\":null,\"nameRole\":null}]}";
        String jsomStringUser3 = "{\"id\":0,\"firstName\":\"another firstname\",\"surname\":\"surname\",\"telephone\":\"+375338569371\",\"login\":\"somelogin\",\"roles\":[{\"id\":1,\"nameRole\":\"User\"},{\"id\":null,\"nameRole\":null}]}";

        userController.create(jsomStringUser1);
        userController.create(jsomStringUser2);

        StringForRead = userController.getById(0L);
        System.out.println(StringForRead);

        userController.deleteById(1L);

        userController.update(jsomStringUser3);

        StringForRead = userController.getById(0L);
        System.out.println(StringForRead);
    }
}
