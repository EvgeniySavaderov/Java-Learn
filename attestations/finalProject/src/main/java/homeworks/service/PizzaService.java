package homeworks.service;

import homeworks.dto.PizzaBaseRequestDto;
import homeworks.dto.PizzaBaseResponseDto;

import java.util.List;

public interface PizzaService {

    List<PizzaBaseResponseDto> getAllPizzaBases();

    PizzaBaseResponseDto findPizzaBaseById(Long id);

    PizzaBaseResponseDto addPizzaBase(PizzaBaseRequestDto pizzaBaseRequestDto);

    PizzaBaseResponseDto findPizzaByName(String name);
}
