package Exceptions;

public class ExcessNameException extends RuntimeException{
    public ExcessNameException() {
        super("Получено лишнее поля имени");
    }
}
