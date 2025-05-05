package homeworks.service;

import homeworks.dto.PizzaOrderRequestDto;
import homeworks.dto.PizzaOrderResponseDto;

import java.util.List;

public interface OrderService {
    List<PizzaOrderResponseDto> getAllPizzaOrders();

    PizzaOrderResponseDto findPizzaOrderById(Long id);

    PizzaOrderResponseDto addPizzaOrder(PizzaOrderRequestDto pizzaOrderRequestDto);

    void deletePizzaOrderById(Long id);
}
