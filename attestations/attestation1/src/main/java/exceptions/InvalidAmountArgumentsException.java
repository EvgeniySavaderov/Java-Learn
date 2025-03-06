package exceptions;

public class InvalidAmountArgumentsException extends RuntimeException {
    public InvalidAmountArgumentsException(String message) {
        super(message);
    }
}
