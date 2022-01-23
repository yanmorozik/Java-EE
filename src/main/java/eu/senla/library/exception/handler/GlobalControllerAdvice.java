package eu.senla.library.exception.handler;

import eu.senla.library.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.core.AuthenticationException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(
            GlobalControllerAdvice.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException notFoundException) {
        logger.error(notFoundException.getLocalizedMessage(), notFoundException);
        return new ResponseEntity<>(notFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> exception(AccessDeniedException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);//??? потому что ошибка генерируется на русском языке и postman не может её перевести на английский
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> exception(AuthenticationException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> notFoundException(Exception exception) {
        logger.error(exception.getLocalizedMessage(), exception);
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
