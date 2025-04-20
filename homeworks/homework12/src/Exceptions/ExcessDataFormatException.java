package Exceptions;

public class ExcessDataFormatException extends RuntimeException {
    public ExcessDataFormatException(String s) {
      super("Добавлено лишнее поле того же формата: "+s);
    }
}
