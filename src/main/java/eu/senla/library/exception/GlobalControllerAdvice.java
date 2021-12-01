package eu.senla.library.exception;

import eu.senla.library.dto.ErrorMessageDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ErrorMessageDto catchException( ){
        return new ErrorMessageDto("entity with id was not found\"");
    }

}
