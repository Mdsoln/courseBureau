package EvaluationBureau.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailHandlerException {

    @ExceptionHandler(value = {EmailException.class})
    public ResponseEntity<Object> handleEmailExceptions(
            EmailException emailException
    ){
        GlobalHandlerFields handlerFields = GlobalHandlerFields.builder()
                .message(emailException.getMessage())
                .throwable(emailException.getCause())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(handlerFields,HttpStatus.BAD_REQUEST);
    }
}
