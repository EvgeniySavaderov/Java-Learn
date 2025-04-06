package homeworks.repository;

import homeworks.model.Orders;
import homeworks.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    @DisplayName("delete all orders test")
    void deleteAllTest() {
        ordersRepository.deleteAll();
        List<Orders> ordersList = ordersRepository.findAll();
        Assertions.assertNotNull(ordersList);
        Assertions.assertEquals(0, ordersList.size());
    }

    @Test
    @DisplayName("find all orders test")
    void findAllTest() {
        List<Orders> ordersList = ordersRepository.findAll();
        Assertions.assertNotNull(ordersList);
        Assertions.assertEquals(10, ordersList.size());
    }

    @Test
    @DisplayName("add order test")
    void addOrderTest() {
        Users user = new Users("Евгений Владимиров");
        Orders order = new Orders(user, LocalDate.now(), 1, 10);
        Orders savedOrder = ordersRepository.save(order);
        Assertions.assertNotNull(savedOrder);
        Assertions.assertEquals(order, savedOrder);
    }

    @Test
    @DisplayName("find order by id test")
    void findByIdTest() {
        Assertions.assertDoesNotThrow(() -> ordersRepository.findById(5L));
        Orders orders = ordersRepository.findById(5L).orElseThrow();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(5L, orders.getId());
        Assertions.assertEquals(Optional.empty(), ordersRepository.findById(17L));
    }

    @Test
    @DisplayName("find orders by date test")
    void findByDateTest() {
        LocalDate localDate = LocalDate.of(2025, 3, 10);
        List<Orders> orders = ordersRepository.findByDateOrder(localDate);
        Assertions.assertEquals(2, orders.size());
    }
}