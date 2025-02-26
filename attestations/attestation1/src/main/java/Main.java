import exceptions.UserNotFoundException;
import model.User;
import repositories.UserRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.deleteAll(); //предварительная очистка
        userRepository.create(new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true")); //создание репозитория
        userRepository.create(new User("a5g8a3cb|2023-12-25T20:10:11.556|noisemc_98|123ghs|123ghs|Крылов|Иван|Павлович|30|true"));
        userRepository.create(new User("b5g8a3cb|2023-12-25T21:10:11.556|noisemc_97|125ghs|125ghs|Крылов|Степан|Павлович|28|false"));

        try { //поиск пользователей
            User user = userRepository.findById("f5g8a3cb");
            System.out.println("Пользователь найден: "+user);
        }
        catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            User user = userRepository.findById("g5g8a3cb");
            System.out.println(user);
        }
        catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        List<User> allUsers = userRepository.findAll(); //вывод всех пользователей
        System.out.println("Все пользователи");
        for(User user: allUsers) {
            System.out.println(user);
        }

        User user2 = new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|30|false"); //обнолений пользователей
        User user3 = new User("q5g8a3cb|2023-12-28T10:07:11.556|mr_52|77ghs|77ghs|Иванов|Петр|Павлович|25|true");
        userRepository.update(user2);
        userRepository.update(user3);
        System.out.println("Все пользователи после обновления: ");
        for(User user: allUsers) {
            System.out.println(user);
        }

        try { //удаление пользователей
            userRepository.deleteById("a5g8a3cb");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            userRepository.deleteById("d5g8a3cb");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Все пользователи после удаления: ");
        for(User user: allUsers) {
            System.out.println(user);
        }


        List<User> usersWorkers = userRepository.findByIsWorker(); //поиск пользователей-работников
        System.out.println("Пользователи-работники:");
        for(User user: usersWorkers) {
            System.out.println(user);
        }
    }
}