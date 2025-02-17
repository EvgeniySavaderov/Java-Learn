import Exceptions.WrongLoginException;
import Exceptions.WrongPasswordException;

public class User {
    private User() {};

    public static boolean validate(String login, String password, String confirmPassword) {
        String patternLogin = "[a-zA-Z0-9_]{1,19}"; //паттерны логина и  пароля
        String patternPassword = "[a-zA-Z0-9_]{1,19}";
        if(!login.matches(patternLogin)) {
            try {
                throw new WrongLoginException("Логин не соответствует требованиям: "+login);
            } catch (WrongLoginException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        if(!password.matches(patternPassword)) {
            try {
                throw new WrongPasswordException("Пароль не соответствует требованиям");
            } catch (WrongPasswordException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        if(!password.equals(confirmPassword)) { //пароли должны совпадать
            try {
                throw new WrongPasswordException("Пароли не совпадают");
            } catch (WrongPasswordException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
