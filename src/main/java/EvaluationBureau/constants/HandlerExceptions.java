package EvaluationBureau.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(value = {HandleExceptions.class})
    public ResponseEntity<Object> handleExceptions(
            HandleExceptions handler
    ){
        GlobalHandlerFields fields = new GlobalHandlerFields(
                handler.getMessage(),
                handler.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(fields,HttpStatus.BAD_REQUEST);
    }
}
