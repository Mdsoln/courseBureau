package EvaluationBureau.constants;

public class NotFoundExceptions extends RuntimeException{

    public NotFoundExceptions(String message) {
        super(message);
    }

    public NotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
