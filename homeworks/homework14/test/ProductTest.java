import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Product")
class ProductTest {
    static class productArgProvider implements ArgumentsProvider { //поставщик параметров конструктора
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("Хлеб", 40),
                    Arguments.of("Молоко", 60),
                    Arguments.of("Торт", 1000)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(productArgProvider.class)
    @DisplayName("Создание Product")
    void createProductTest(String name, int cost) {
        Assertions.assertDoesNotThrow(() -> new Product(name, cost));
    }

    @Test
    @DisplayName("Исключения Product")
    void exceptionsTest() {
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("", 20)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("Молоко", -5))
        );
    }

    @Test
    @Disabled
    @DisplayName("Печать Product")
    void testToString() {
        Product p = new Product("Торт", 1000);
        Assertions.assertEquals("Торт Цена: 1000", p.toString());
    }

    @Test
    @DisplayName("Равенство объектов Product")
    void testEquals() {
        Product p = new Product("Торт", 1000);
        Product p2 = new Product("Торт", 1000);
        Product p3 = new Product("Кекс", 1000);
        Product p4 = new Product("Торт", 800);
        Assertions.assertAll(
                () -> Assertions.assertEquals(p, p2),
                () -> Assertions.assertNotEquals(p, p3),
                () -> Assertions.assertNotEquals(p, p4)
        );
    }

    @Test
    @DisplayName("Хэш-код Product")
    void testHashCode() {
        Product p = new Product("Торт", 1000);
        Product p2 = new Product("Торт", 1000);
        Assertions.assertEquals(p.hashCode(), p2.hashCode());
    }
}