package homeworks.service;

import homeworks.dto.*;

import java.util.List;

public interface PizzaService {

    List<PizzaBaseResponseDto> getAllPizzaBases();

    PizzaBaseResponseDto findPizzaBaseById(Long id);

    PizzaBaseResponseDto addPizzaBase(PizzaBaseRequestDto pizzaBaseRequestDto);

    PizzaBaseResponseDto findPizzaByName(String name);
}
