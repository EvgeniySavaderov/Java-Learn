package model;

import exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Тест класса User")
public class UserTest {


    @ParameterizedTest
    @ValueSource(strings = {"f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true",
            "f5g8a3cb-4ac9||noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true",
            "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор||25|true",
            "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович||true",
            "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|"})
    @DisplayName("Позитивные тесты")
    public void testPositive(String input) {
        Assertions.assertDoesNotThrow(() -> new User(input));
    }

    @Test
    @DisplayName("Заполение пустых полей")
    public void fillEmptyTest() {
        String input = "f5g8a3cb-4ac9||noisemc_99|789ghs|789ghs|Крылов|Виктор|||";
        Assertions.assertDoesNotThrow(() -> new User(input));
        User user = new User(input);
        Assertions.assertAll(() -> Assertions.assertNotNull(user.getLocalDateTime()), //проверка значений по умолчанию
                () -> Assertions.assertEquals("", user.getPatronymic()),
                () -> Assertions.assertEquals(0, user.getAge()),
                () -> Assertions.assertFalse(user.isWorker()));
    }

    @Test
    @DisplayName("Тесты на исключеия")
    public void exceptionsTest() {
        String input1 = "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25"; //неверное число полей
        String input2 = "f5g8a3cb-4ac9@|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"; //некорректный id
        String input3 = "f5g8a3cb-4ac9|202319:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"; //некорректная дата
        String input4 = "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|1234|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"; //логин только из цифр
        String input5 = "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|ghs|ghs|Крылов|Виктор|Павлович|25|true"; //пароль только из букв
        String input6 = "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|689ghs|Крылов|Виктор|Павлович|25|true"; //пароли не совпадают
        String input7 = "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|В@ктор|Павлович|25|true"; //некорректная фамилия
        String input8 = "f5g8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|abc|true"; //некорректный возраст

        Assertions.assertAll(() -> Assertions.assertThrows(InvalidAmountArgumentsException.class, () -> new User(input1)),
                () -> Assertions.assertThrows(InvalidIdException.class, () -> new User(input2)),
                () -> Assertions.assertThrows(InvalidDateException.class, () -> new User(input3)),
                () -> Assertions.assertThrows(InvalidLoginException.class, () -> new User(input4)),
                () -> Assertions.assertThrows(InvalidPasswordException.class, () -> new User(input5)),
                () -> Assertions.assertThrows(InvalidPasswordException.class, () -> new User(input6)),
                () -> Assertions.assertThrows(InvalidNameException.class, () -> new User(input7)),
                () -> Assertions.assertThrows(InvalidAgeException.class, () -> new User(input8)));
    }
}
