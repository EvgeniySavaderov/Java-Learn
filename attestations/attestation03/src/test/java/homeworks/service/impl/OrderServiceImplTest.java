package homeworks.service.impl;

import homeworks.dto.PizzaOrderRequestDto;
import homeworks.dto.PizzaOrderResponseDto;
import homeworks.mapper.PizzaOrderRequestMapper;
import homeworks.mapper.PizzaOrderResponseMapper;
import homeworks.model.PizzaBase;
import homeworks.model.PizzaOrder;
import homeworks.repository.PizzaOrderRepository;
import homeworks.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = OrderServiceImpl.class)
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @MockitoBean
    private PizzaOrderRepository pizzaOrderRepository;
    @MockitoBean
    private PizzaOrderRequestMapper pizzaOrderRequestMapper;
    @MockitoBean
    private PizzaOrderResponseMapper pizzaOrderResponseMapper;

    @Test
    void getAllPizzaOrders() {
        List<PizzaOrder> pizzaOrders = new ArrayList<>();
        pizzaOrders.add(new PizzaOrder(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200))));
        PizzaOrderResponseDto pizzaOrderResponseDto = new PizzaOrderResponseDto(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));

        Mockito.when(pizzaOrderRepository.findAll()).thenReturn(pizzaOrders);
        Mockito.when(pizzaOrderResponseMapper.toPizzaOrderResponseDto(any(PizzaOrder.class))).thenReturn(pizzaOrderResponseDto);

        List<PizzaOrderResponseDto> allOrders = orderService.getAllPizzaOrders();

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(allOrders);
            Assertions.assertEquals(1, allOrders.size());
        });
    }

    @Test
    void findPizzaOrderById() {
        PizzaOrder foundOrder = new PizzaOrder(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PizzaOrderResponseDto foundOrderResponse = new PizzaOrderResponseDto(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));

        Mockito.when(pizzaOrderRepository.findById(1L)).thenReturn(Optional.of(foundOrder));
        Mockito.when(pizzaOrderResponseMapper.toPizzaOrderResponseDto(foundOrder)).thenReturn(foundOrderResponse);

        PizzaOrderResponseDto pizzaOrderResponseDto = orderService.findPizzaOrderById(1L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(pizzaOrderResponseDto);
            Assertions.assertEquals(1L, pizzaOrderResponseDto.id());
        });
    }

    @Test
    void addPizzaOrder() {
        PizzaOrderRequestDto addOrderRequest = new PizzaOrderRequestDto(null, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PizzaOrder pizzaOrder = new PizzaOrder(null, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PizzaOrder addedOrder = new PizzaOrder(2L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PizzaOrderResponseDto addedOrderResponse = new PizzaOrderResponseDto(2L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));

        Mockito.when(pizzaOrderRequestMapper.toEntity(addOrderRequest)).thenReturn(pizzaOrder);
        Mockito.when(pizzaOrderRepository.save(pizzaOrder)).thenReturn(addedOrder);
        Mockito.when(pizzaOrderResponseMapper.toPizzaOrderResponseDto(addedOrder)).thenReturn(addedOrderResponse);

        PizzaOrderResponseDto pizzaOrderResponseDto = orderService.addPizzaOrder(addOrderRequest);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(pizzaOrderResponseDto);
            Assertions.assertEquals(2L, pizzaOrderResponseDto.id());
        });
    }
}