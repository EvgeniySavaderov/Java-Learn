package Exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(String s) {
        super("Некоректная дата: "+s);
    }
}
