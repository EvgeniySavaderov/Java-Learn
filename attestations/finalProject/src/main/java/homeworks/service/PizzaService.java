package homeworks.service;

import homeworks.dto.*;

import java.util.List;

public interface PizzaService {
    List<PersonResponseDto> getAllPersons();

    PersonResponseDto findPersonById(Long id);

    PersonResponseDto addPerson(PersonRequestDto personRequestDto);

    void deletePersonById(Long id);

    List<PizzaBaseResponseDto> getAllPizzaBases();

    PizzaBaseResponseDto findPizzaBaseById(Long id);

    PizzaBaseResponseDto addPizzaBase(PizzaBaseRequestDto pizzaBaseRequestDto);

    List<PizzaOrderResponseDto> getAllPizzaOrders();

    PizzaOrderResponseDto findPizzaOrderById(Long id);

    PizzaOrderResponseDto addPizzaOrder(PizzaOrderRequestDto pizzaOrderRequestDto);

    void deletePizzaOrderById(Long id);

    PersonResponseDto findPersonByFio(String fio);

    PizzaBaseResponseDto findPizzaByName(String name);

    PersonResponseDto addOrderForPersonById(Long id, PizzaOrderRequestDto pizzaOrderRequestDto);

    PersonResponseDto addOrderForPersonByFio(String Fio, PizzaOrderRequestDto pizzaOrderRequestDto);
}
