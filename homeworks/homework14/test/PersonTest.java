import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

@DisplayName("Класс Person")
class PersonTest {
    static class personArgProvider implements ArgumentsProvider { //поставщик параметров конструктора
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("Павел Андреевич", 10000),
                    Arguments.of("Анна Петровна", 2000),
                    Arguments.of("Борис", 10)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(personArgProvider.class)
    @DisplayName("Создание Person")
    void createPersonTest(String name, int money) {
        Assertions.assertDoesNotThrow(() -> new Person(name, money));
    }

    @Test
    @DisplayName("Исключения Person")
    void exceptionsTest() {
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("", 10)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("Света", -3))
        );
    }

    @Test
    @DisplayName("Покупка продуктов")
    void addProduct() {
        Person person = new Person("Павел", 2000);
        Person person2 = new Person("Женя", 50);
        Product coffee = new Product("Кофе растворимый", 879);
        Product milk = new Product("Молоко", 60);
        Product cake = new Product("Торт", 1000);
        Assertions.assertAll(
                () -> Assertions.assertEquals("Павел купил Кофе растворимый", person.AddProduct(coffee)),
                () -> Assertions.assertEquals("Павел купил Молоко", person.AddProduct(milk)),
                () -> Assertions.assertEquals("Павел купил Молоко", person.AddProduct(milk)),
                () -> Assertions.assertEquals("Павел купил Молоко", person.AddProduct(milk)),
                () -> Assertions.assertEquals("Павел не может позволить себе Торт", person.AddProduct(cake)),
                () -> Assertions.assertEquals("Павел купил Кофе растворимый", person.AddProduct(coffee)),
                () -> Assertions.assertEquals("Женя не может позволить себе Молоко", person2.AddProduct(milk))
        );
    }

    @Test
    @DisplayName("Печать Person")
    void testToString() {
        Person person = new Person("Павел", 2000);
        Product coffee = new Product("Кофе растворимый", 879);
        Product milk = new Product("Молоко", 60);
        Product cake = new Product("Торт", 1000);
        Assertions.assertEquals("Павел - Ничего не куплено", person.toString());
        person.AddProduct(coffee);
        person.AddProduct(milk);
        person.AddProduct(milk);
        person.AddProduct(milk);
        person.AddProduct(cake);
        Assertions.assertEquals("Павел - Кофе растворимый, Молоко, Молоко, Молоко", person.toString());
    }

    @Test
    @DisplayName("Равенство объектов Person")
    void testEquals() {
        Person person = new Person("Павел", 2000);
        Person person2 = new Person("Павел", 2000);
        Person person3 = new Person("Женя", 2000);
        Person person4 = new Person("Павел", 100);
        Assertions.assertAll(
                () -> Assertions.assertEquals(person, person2),
                () -> Assertions.assertNotEquals(person, person3),
                () -> Assertions.assertNotEquals(person, person4)
        );
        Product milk = new Product("Молоко", 60);
        person.AddProduct(milk);
        Assertions.assertNotEquals(person, person2);
        person2.AddProduct(milk);
        Assertions.assertEquals(person, person2);
    }

    @Test
    @DisplayName("Хэш-код объектов Person")
    void testHashCode() {
        Person person = new Person("Павел", 2000);
        Person person2 = new Person("Павел", 2000);
        Assertions.assertEquals(person.hashCode(), person2.hashCode());
        Product milk = new Product("Молоко", 60);
        person.AddProduct(milk);
        person2.AddProduct(milk);
        Assertions.assertEquals(person.hashCode(), person2.hashCode());
    }
}