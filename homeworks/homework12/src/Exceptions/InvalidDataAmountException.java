package Exceptions;

public class InvalidDataAmountException extends RuntimeException {
    public InvalidDataAmountException(int numFields, int actualNumFields) {
        super("Получено "+numFields+ " полей, но требуется "+actualNumFields);
    }
}