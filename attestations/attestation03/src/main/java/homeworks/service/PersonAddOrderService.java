package homeworks.service;

import homeworks.dto.PersonResponseDto;
import homeworks.dto.PizzaOrderRequestDto;

public interface PersonAddOrderService {
    PersonResponseDto addOrderForPersonById(Long id, PizzaOrderRequestDto pizzaOrderRequestDto);

    PersonResponseDto addOrderForPersonByFio(String Fio, PizzaOrderRequestDto pizzaOrderRequestDto);
}
