package run.api.Exception;

public class StudentException extends Throwable{
    public StudentException(String message) {
        super(message);
    }

    public StudentException(String message, Throwable cause) {
        super(message, cause);
    }
}
