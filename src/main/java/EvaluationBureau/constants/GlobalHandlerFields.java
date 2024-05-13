package EvaluationBureau.constants;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class GlobalHandlerFields {
    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;
}
