package EvaluationBureau.constants;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(value = {NotFoundExceptions.class})
    public ResponseEntity<Object> handleException(
            NotFoundExceptions notFoundExceptions
    ){
        GlobalHandlerFields globalHandlerFields =
                GlobalHandlerFields.builder()
                        .message(notFoundExceptions.getMessage())
                        .throwable(notFoundExceptions.getCause())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();

        return new ResponseEntity<>(globalHandlerFields,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
