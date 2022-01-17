package eu.senla.library.filter;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookProtocol {
    private final String bookName,
            description,
            numberOfPage,
            yearOfPublishing,
            numberOfCopies,
            nameGenre,
            nameLanguage,
            firstNameAuthor,
            surnameAuthor,
            namePublisher,
            telephonePublisher;

    public BookProtocol(String bookName,
                        String description,
                        String numberOfPage,
                        String yearOfPublishing,
                        String numberOfCopies,
                        String nameGenre,
                        String nameLanguage,
                        String firstNameAuthor,
                        String surnameAuthor,
                        String namePublisher,
                        String telephonePublisher) {
        this.bookName = bookName;
        this.description = description;
        this.numberOfPage = numberOfPage;
        this.yearOfPublishing = yearOfPublishing;
        this.numberOfCopies = numberOfCopies;
        this.nameGenre = nameGenre;
        this.nameLanguage = nameLanguage;
        this.firstNameAuthor = firstNameAuthor;
        this.surnameAuthor = surnameAuthor;
        this.namePublisher = namePublisher;
        this.telephonePublisher = telephonePublisher;
    }
}
