package repositories;

import exceptions.UserNotFoundException;
import model.User;
import org.junit.jupiter.api.*;

@DisplayName("Тестирование репозитория")
public class UserRepositoryImplTest {
    private static final UserRepositoryImpl USERS_TEST = new UserRepositoryImpl();

    @BeforeEach
    public void clear() {
        USERS_TEST.deleteAll();
    }

    @Test
    @DisplayName("Очистка списка")
    public void deleteAllTest() {
        Assertions.assertEquals(0, USERS_TEST.findAll().size());
    }

    @Test
    @DisplayName("Создание пользователей")
    public void createTest() {
        USERS_TEST.create(new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"));
        USERS_TEST.create(new User("a5g8a3cb|2023-12-25T20:10:11.556|noisemc_98|123ghs|123ghs|Крылов|Иван|Павлович|30|true"));
        USERS_TEST.create(new User("b5g8a3cb|2023-12-25T21:10:11.556|noisemc_99|125ghs|125ghs|Крылов|Степан|Павлович|28|false"));
        Assertions.assertEquals(3, USERS_TEST.findAll().size());
    }

    @Test
    @DisplayName("Поиск пользователя")
    public void findByIdTest() {
        USERS_TEST.create(new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"));
        USERS_TEST.create(new User("a5g8a3cb|2023-12-25T20:10:11.556|noisemc_98|123ghs|123ghs|Крылов|Иван|Павлович|30|true"));
        USERS_TEST.create(new User("b5g8a3cb|2023-12-25T21:10:11.556|noisemc_99|125ghs|125ghs|Крылов|Степан|Павлович|28|false"));
        Assertions.assertAll(() -> Assertions.assertDoesNotThrow(() -> USERS_TEST.findById("f5g8a3cb")),
                () -> Assertions.assertEquals("b5g8a3cb", USERS_TEST.findById("b5g8a3cb").getId()), //пользователь по id найден
                () -> Assertions.assertThrows(UserNotFoundException.class, () -> USERS_TEST.findById("g5g8a3cb"))); //пользователь по id не найден
    }

    @Test
    @DisplayName("Обновление пользователя")
    public void updateTest() {
        USERS_TEST.create(new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"));
        USERS_TEST.create(new User("a5g8a3cb|2023-12-25T20:10:11.556|noisemc_98|123ghs|123ghs|Крылов|Иван|Павлович|30|true"));
        USERS_TEST.create(new User("b5g8a3cb|2023-12-25T21:10:11.556|noisemc_99|125ghs|125ghs|Крылов|Степан|Павлович|28|false"));
        User updatedUser1 = new User("a5g8a3cb|2023-12-25T23:10:11.556|noisemc_92|123qwy|123qwy|Крылов|Иван|Павлович|32|false");
        User updatedUser2 = new User("g5g8a3cb|2023-12-25T23:42:11.556|mc_71|456qwy|456qwy|Иванов|Иван|Павлович|29|true");
        USERS_TEST.update(updatedUser1);
        USERS_TEST.update(updatedUser2);
        Assertions.assertAll(() -> Assertions.assertEquals(updatedUser1, USERS_TEST.findById(updatedUser1.getId())), //обновление пользователя
                () -> Assertions.assertEquals(4, USERS_TEST.findAll().size())); //добавление нового пользователя
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void deleteTest() {
        USERS_TEST.create(new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"));
        USERS_TEST.create(new User("a5g8a3cb|2023-12-25T20:10:11.556|noisemc_98|123ghs|123ghs|Крылов|Иван|Павлович|30|true"));
        USERS_TEST.create(new User("b5g8a3cb|2023-12-25T21:10:11.556|noisemc_99|125ghs|125ghs|Крылов|Степан|Павлович|28|false"));
        Assertions.assertAll(() -> Assertions.assertDoesNotThrow(() -> USERS_TEST.deleteById("f5g8a3cb")),
                () -> Assertions.assertEquals(2, USERS_TEST.findAll().size()), //пользователь удален
                () -> Assertions.assertThrows(UserNotFoundException.class, () -> USERS_TEST.deleteById("a"))); //пользователь не найден
    }

    @Test
    @DisplayName("Поиск работников")
    public void findByIsWorkerTest() {
        USERS_TEST.create(new User("f5g8a3cb|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"));
        USERS_TEST.create(new User("a5g8a3cb|2023-12-25T20:10:11.556|noisemc_98|123ghs|123ghs|Крылов|Иван|Павлович|30|true"));
        USERS_TEST.create(new User("b5g8a3cb|2023-12-25T21:10:11.556|noisemc_99|125ghs|125ghs|Крылов|Степан|Павлович|28|false"));
        Assertions.assertEquals(2, USERS_TEST.findByIsWorker().size()); //найдены все работники
    }
}
