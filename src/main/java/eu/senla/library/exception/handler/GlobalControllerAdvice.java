package eu.senla.library.exception.handler;

import eu.senla.library.exception.AuthorNotFoundException;
import eu.senla.library.exception.BookNotFoundException;
import eu.senla.library.exception.BookingNotFoundException;
import eu.senla.library.exception.CredentialNotFoundException;
import eu.senla.library.exception.GenreNotFoundException;
import eu.senla.library.exception.LanguageNotFoundException;
import eu.senla.library.exception.PublisherNotFoundException;
import eu.senla.library.exception.RoleNotFoundException;
import eu.senla.library.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> notFoundException(AuthorNotFoundException authorNotFoundException){
        return new ResponseEntity<>(authorNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> notFoundException(BookNotFoundException BookNotFoundException){
        return new ResponseEntity<>(BookNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> notFoundException(BookingNotFoundException bookingNotFoundException){
        return new ResponseEntity<>(bookingNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CredentialNotFoundException.class)
    public ResponseEntity<String> notFoundException(CredentialNotFoundException credentialNotFoundException){
        return new ResponseEntity<>(credentialNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<String> notFoundException(GenreNotFoundException genreNotFoundException){
        return new ResponseEntity<>(genreNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LanguageNotFoundException.class)
    public ResponseEntity<String> notFoundException(LanguageNotFoundException languageNotFoundException){
        return new ResponseEntity<>(languageNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublisherNotFoundException.class)
    public ResponseEntity<String> notFoundException(PublisherNotFoundException publisherNotFoundException){
        return new ResponseEntity<>(publisherNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> notFoundException(RoleNotFoundException roleNotFoundException){
        return new ResponseEntity<>(roleNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> notFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> notFoundException(Exception exception){
        return new ResponseEntity<>(exception.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
