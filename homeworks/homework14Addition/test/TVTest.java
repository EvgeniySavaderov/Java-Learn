import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс TV")
class TVTest {

    @Test
    @DisplayName("Включение/выключение")
    void changeState() {
        TV tv1 = new TV("Samsung", 20, true, true, 23, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 23, 60);
        Assumptions.assumeTrue(tv1.equals(tv2));
        tv2.changeState();
        Assertions.assertNotEquals(tv1, tv2);
        tv2.changeState();
        Assertions.assertEquals(tv1, tv2);
    }

    @Test
    @DisplayName("Следующий канал")
    void nextChannel() {
        TV tv1 = new TV("Samsung", 20, true, true, 23, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv3 = new TV("Samsung", 20, true, true, 99, 60);
        TV tv4 = new TV("Samsung", 20, true, true, 0, 60);
        TV tv5 = new TV("Samsung", 20, true, false, 23, 60); //нельзе переключить выключенный телевизор
        TV tv6 = new TV("Samsung", 20, true, false, 24, 60);
        Assumptions.assumeFalse(tv1.equals(tv2) || tv3.equals(tv4) || tv5.equals(tv6));
        tv1.nextChannel();
        tv3.nextChannel();
        tv5.nextChannel();
        Assertions.assertAll(
                () -> Assertions.assertEquals(tv1, tv2),
                () -> Assertions.assertEquals(tv3, tv4),
                () -> Assertions.assertNotEquals(tv5, tv6)
        );
    }

    @Test
    @DisplayName("Предыдущий канал")
    void prevChannel() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 23, 60);
        TV tv3 = new TV("Samsung", 20, true, true, 0, 60);
        TV tv4 = new TV("Samsung", 20, true, true, 99, 60);
        TV tv5 = new TV("Samsung", 20, true, false, 24, 60); //нельзе переключить выключенный телевизор
        TV tv6 = new TV("Samsung", 20, true, false, 23, 60);
        Assumptions.assumeFalse(tv1.equals(tv2) || tv3.equals(tv4) || tv5.equals(tv6));
        tv1.prevChannel();
        tv3.prevChannel();
        tv5.prevChannel();
        Assertions.assertAll(
                () -> Assertions.assertEquals(tv1, tv2),
                () -> Assertions.assertEquals(tv3, tv4),
                () -> Assertions.assertNotEquals(tv5, tv6)
        );
    }

    @Test
    @DisplayName("Перейти на канал")
    void goToChannel() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 56, 60);
        TV tv3 = new TV("Samsung", 20, true, false, 24, 60); //нельзе переключить выключенный телевизор
        TV tv4 = new TV("Samsung", 20, true, false, 56, 60);
        Assumptions.assumeFalse(tv1.equals(tv2) || tv3.equals(tv4));
        tv1.goToChannel(56);
        tv3.goToChannel(56);
        Assertions.assertAll(
                () -> Assertions.assertEquals(tv1, tv2),
                () -> Assertions.assertNotEquals(tv3, tv4)
        );
    }

    @Test
    @DisplayName("Печать объекта ")
    void testToString() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        Assertions.assertEquals("model: Samsung sizeInches: 20 is smart tv: true\nis enabled: true current channel: 24 volume: 60", tv1.toString());
    }

    @Test
    @DisplayName("Фильтр громкости")
    void isAllowedVolume() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv2 = new TV("Samsung", 20, true, false, 24, 60);
        TV tv3 = new TV("Samsung", 20, true, true, 24, 80);
        Assertions.assertAll(
                () -> Assertions.assertTrue(tv1.isAllowedVolume(60)),
                () -> Assertions.assertFalse(tv2.isAllowedVolume(60)),
                () -> Assertions.assertFalse(tv3.isAllowedVolume(60))
        );
    }

    @Test
    @DisplayName("Сравение объектов TV")
    void compareTo() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 17, 60);
        TV tv3 = new TV("Samsung", 20, true, true, 45, 60);
        Assertions.assertAll(
                () -> Assertions.assertTrue(tv1.compareTo(tv2)>0),
                () -> Assertions.assertTrue(tv1.compareTo(tv3)<0),
                () -> Assertions.assertTrue(tv2.compareTo(tv3)<0)
        );
    }

    @Test
    @DisplayName("Равенство объектов TV")
    void testEquals() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv3 = new TV("Samsung", 20, true, false, 45, 60);
        Assertions.assertAll(
                () -> Assertions.assertEquals(tv1, tv2),
                () -> Assertions.assertNotEquals(tv1, tv3)
        );
    }

    @Test
    @DisplayName("Хэш-код объектов TV")
    void testHashCode() {
        TV tv1 = new TV("Samsung", 20, true, true, 24, 60);
        TV tv2 = new TV("Samsung", 20, true, true, 24, 60);
        Assumptions.assumeTrue(tv1.equals(tv2));
        Assertions.assertEquals(tv1.hashCode(), tv2.hashCode());
    }
}