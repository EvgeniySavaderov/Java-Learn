package homeworks.repository;

import homeworks.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("find all users test")
    void findAllTest() {
        Users user1 = new Users("Евгений Владимиров");
        Users user2 = new Users("Дмитрий Троицкий");
        Users user3 = new Users("Арина Чернова");
        usersRepository.save(user1);
        usersRepository.save(user2);
        usersRepository.save(user3);
        List<Users> usersList = usersRepository.findAll();
        Assertions.assertNotNull(usersList);
        Assertions.assertEquals(13, usersList.size());
    }

    @Test
    @DisplayName("delete all users test")
    void deleteAllTest() {
        usersRepository.deleteAll();
        List<Users> usersList = usersRepository.findAll();
        Assertions.assertNotNull(usersList);
        Assertions.assertEquals(0, usersList.size());
    }

    @Test
    @DisplayName("add user test")
    void adduserTest() {
        Users user1 = new Users("Евгений Владимиров");
        Users savedUser = usersRepository.save(user1);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user1, savedUser);
    }

    @Test
    @DisplayName("find user by id test")
    void findByIdTest() {
        Assertions.assertDoesNotThrow(() -> usersRepository.findById(5L));
        Users user = usersRepository.findById(5L).orElseThrow();
        Assertions.assertNotNull(user);
        Assertions.assertEquals(5L, user.getId());
        Assertions.assertEquals(Optional.empty(), usersRepository.findById(17L));
    }
}
