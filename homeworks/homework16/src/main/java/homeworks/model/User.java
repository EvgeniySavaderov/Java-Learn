package homeworks.model;

import homeworks.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

public class User {
    private String id;
    private LocalDateTime localDateTime;
    private String login;
    private String password;
    private String confirmPassword;
    private String name;
    private String lastName;
    private String patronymic;
    private int age;
    private boolean isWorker;

    private static final int numArgs = 10; //количество полей
    private static final String idPattern = "[a-zA-Z0-9\\-]+"; //паттерны полей
    private static final String loginPasswordPattern = "[a-zA-Z0-9_]{1,19}";
    private static final String digitsOnlyPattern = "[0-9]+";
    private static final String lettersOnlyPattern = "[a-zA-Z]+";
    private static final String namePattern = "[а-яА-Я]+";

    public User (String s) {
        String[] args = s.split("\\|", -1);
        if(args.length!=numArgs) {
            throw new InvalidAmountArgumentsException("Предоставлено "+args.length+" аргументов, но требуется "+numArgs);
        }

        if(!args[0].matches(idPattern)) {
            throw new InvalidIdException("Некоррекнтный id: "+args[0]);
        }
        id = args[0];

        if(args[1].isEmpty()) {
            localDateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS")));
        }
        else {
            try {
                localDateTime = LocalDateTime.parse(args[1], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS"));
            }
            catch (DateTimeParseException e) {
                throw new InvalidDateException("Некорректная дата: "+args[1]);
            }
        }

        if(!args[2].matches(loginPasswordPattern) || args[2].matches(digitsOnlyPattern)) {
            throw new InvalidLoginException("Некорректный логин: "+args[2]);
        }
        login = args[2];

        if(!args[3].matches(loginPasswordPattern) || args[3].matches(lettersOnlyPattern)) {
            throw new InvalidPasswordException("Некорректный пароль");
        }
        password = args[3];

        if(!args[4].equals(password)) {
            throw new InvalidPasswordException("Пароли не совпадают");
        }
        confirmPassword = args[4];

        if(!args[5].matches(namePattern)) {
            throw new InvalidNameException("Некорректное имя: "+args[5]);
        }
        name = args[5];

        if(!args[6].matches(namePattern)) {
            throw new InvalidNameException("Некорректная фамилия: "+args[6]);
        }
        lastName = args[6];

        if(!args[7].isEmpty() && !args[7].matches(namePattern)) {
            throw new InvalidNameException("Некорректное отчество: "+args[7]);
        }
        patronymic = args[7];

        if(!args[8].isEmpty()) {
            try {
                age = Integer.parseInt(args[8]);
            } catch (NumberFormatException e) {
                throw new InvalidAgeException("Некоректный возраст: "+args[8]);
            }
        }
        else {
            age = 0;
        }

        isWorker = Boolean.parseBoolean(args[9]);
    }

    @Override
    public String toString() { //строковое представление
        StringJoiner stringJoiner = new StringJoiner("|");
        stringJoiner.add(id);
        stringJoiner.add(localDateTime.toString());
        stringJoiner.add(login);
        stringJoiner.add(password);
        stringJoiner.add(confirmPassword);
        stringJoiner.add(name);
        stringJoiner.add(lastName);
        stringJoiner.add(patronymic);
        if(age==0) {
            stringJoiner.add("");
        }
        else {
            stringJoiner.add(Integer.toString(age));
        }
        stringJoiner.add(Boolean.toString(isWorker));
        return stringJoiner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass()!=this.getClass()) return false;

        User user2 = (User) o;
        if(!id.equals(user2.getId())) return false;
        if(!login.equals(user2.getLogin())) return false;
        if(!password.equals(user2.getPassword())) return false;
        if(!name.equals(user2.getName())) return false;
        if(!lastName.equals(user2.getLastName())) return false;
        if(!patronymic.equals(user2.getPatronymic())) return false;
        if(age!=user2.getAge()) return false;
        return isWorker == user2.isWorker();
    }

    @Override
    public int hashCode() {
        int res = id.hashCode();
        res = 31 * res + login.hashCode();
        res = 31 * res + password.hashCode();
        res = 31 * res + name.hashCode();
        res = 31 * res + lastName.hashCode();
        res = 31 * res + patronymic.hashCode();
        res = 31 * res + age;
        res = 31 * res + (isWorker ? 1 : 0);
        return res;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
