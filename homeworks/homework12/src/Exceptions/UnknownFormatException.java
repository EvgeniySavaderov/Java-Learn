package Exceptions;

public class UnknownFormatException extends RuntimeException{
    public UnknownFormatException(String s) {
        super("Неизвестный формат поля: "+s);
    }
}
