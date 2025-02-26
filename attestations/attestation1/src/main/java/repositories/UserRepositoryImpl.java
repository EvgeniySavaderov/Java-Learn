package repositories;

import exceptions.*;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UsersRepository{
    private static final List<User> USERS = new ArrayList<>(); //список пользователей
    private static final String PATH = "Users.txt"; //путь

    public UserRepositoryImpl() { //считывание файла с формированием списка
        try(BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine())!=null) {
                try {
                    USERS.add(new User(line));
                }
                catch (InvalidAmountArgumentsException | InvalidIdException | InvalidLoginException |
                       InvalidPasswordException | InvalidNameException | InvalidAgeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(User user) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(PATH, true))) {
            writter.write(user+"\n");
            USERS.add(user);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public User findById(String id) throws UserNotFoundException{
        Optional<User> userWithId = USERS.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        return userWithId.orElseThrow(() -> {return new UserNotFoundException("Пользователь с id "+id+" не найден");});
    }

    public List<User> findAll() {
        return USERS;
    }

    public void update(User user) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(PATH))) {
            List<User> updatedUsers = new ArrayList<>(USERS.stream()
                    .map(user1 -> user1.getId().equals(user.getId()) ? user : user1)
                    .toList());
            if(updatedUsers.equals(USERS)) { //создание нового пользователя с новым id
                System.out.println("Пользователь с id "+user.getId()+" не найден, создание нового пользователя");
                USERS.add(user);
            }
            else {
                USERS.clear();
                USERS.addAll(updatedUsers);
            }
            for(User user2: USERS) {
                writter.write(user2.toString()+"\n");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(String id) throws UserNotFoundException{
        Optional<User> deletedUser = USERS.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        if(deletedUser.isEmpty()) {
            throw new UserNotFoundException("Пользователь с id " + id + " не найден");
        }

        try (BufferedWriter writter = new BufferedWriter(new FileWriter(PATH))) { //обновление списка
            List<User> updatedUsers = new ArrayList<>(USERS.stream()
                    .filter(user -> !user.getId().equals(id))
                    .toList());
            USERS.clear();
            USERS.addAll(updatedUsers);
            for(User user : USERS) {
                writter.write(user.toString()+"\n");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll() {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(PATH))) {
            writter.write("");
            USERS.clear();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> findByIsWorker() {
        return new ArrayList<>(USERS.stream()
                .filter(User::isWorker)
                .toList());
    }
}
