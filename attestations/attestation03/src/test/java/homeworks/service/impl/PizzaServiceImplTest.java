package homeworks.service.impl;

import homeworks.dto.*;
import homeworks.mapper.*;
import homeworks.model.PizzaBase;
import homeworks.repository.PizzaBaseRepository;
import homeworks.service.PizzaService;
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

@SpringBootTest(classes = PizzaServiceImpl.class)
class PizzaServiceImplTest {
    @Autowired
    private PizzaService pizzaService;
    @MockitoBean
    private PizzaBaseRepository pizzaBaseRepository;
    @MockitoBean
    private PizzaBaseRequestMapper pizzaBaseRequestMapper;
    @MockitoBean
    private PizzaBaseResponseMapper pizzaBaseResponseMapper;

    @Test
    void getAllPizzaBases() {
        List<PizzaBase> pizzaBases = new ArrayList<>();
        pizzaBases.add(new PizzaBase(1L, "pepperoni", 250));
        PizzaBaseResponseDto pizzaBaseResponseDto = new PizzaBaseResponseDto(1L, "pepperoni", 250);

        Mockito.when(pizzaBaseRepository.findAll()).thenReturn(pizzaBases);
        Mockito.when(pizzaBaseResponseMapper.toPizzaBaseResponseDto(any(PizzaBase.class))).thenReturn(pizzaBaseResponseDto);

        List<PizzaBaseResponseDto> allPizzas = pizzaService.getAllPizzaBases();

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(allPizzas);
            Assertions.assertEquals(1, allPizzas.size());
        });
    }

    @Test
    void findPizzaBaseById() {
        PizzaBase foundPizza = new PizzaBase(1L, "pepperoni", 250);
        PizzaBaseResponseDto foundPizzaResponse = new PizzaBaseResponseDto(1L, "pepperoni", 250);

        Mockito.when(pizzaBaseRepository.findById(1L)).thenReturn(Optional.of(foundPizza));
        Mockito.when(pizzaBaseResponseMapper.toPizzaBaseResponseDto(foundPizza)).thenReturn(foundPizzaResponse);

        PizzaBaseResponseDto pizzaBaseResponseDto = pizzaService.findPizzaBaseById(1L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(pizzaBaseResponseDto);
            Assertions.assertEquals(1L, pizzaBaseResponseDto.id());
        });
    }

    @Test
    void addPizzaBase() {
        PizzaBaseRequestDto addPizzaRequest = new PizzaBaseRequestDto(null, "pepperoni", 300);
        PizzaBase pizzaBase = new PizzaBase(null, "pepperoni", 300);
        PizzaBase addedPizza = new PizzaBase(3L, "pepperoni", 300);
        PizzaBaseResponseDto addedPizzaResponse = new PizzaBaseResponseDto(3L, "pepperoni", 300);

        Mockito.when(pizzaBaseRequestMapper.toEntity(addPizzaRequest)).thenReturn(pizzaBase);
        Mockito.when(pizzaBaseRepository.save(pizzaBase)).thenReturn(addedPizza);
        Mockito.when(pizzaBaseResponseMapper.toPizzaBaseResponseDto(addedPizza)).thenReturn(addedPizzaResponse);

        PizzaBaseResponseDto pizzaBaseResponseDtoResponseDto = pizzaService.addPizzaBase(addPizzaRequest);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(pizzaBaseResponseDtoResponseDto);
            Assertions.assertEquals(3L, pizzaBaseResponseDtoResponseDto.id());
        });
    }

    @Test
    void findPizzaByName() {
        PizzaBase foundPizza = new PizzaBase(1L, "pepperoni", 250);
        PizzaBaseResponseDto foundPizzaResponse = new PizzaBaseResponseDto(1L, "pepperoni", 250);

        Mockito.when(pizzaBaseRepository.findPizzaByName("pepperoni")).thenReturn(List.of(foundPizza));
        Mockito.when(pizzaBaseResponseMapper.toPizzaBaseResponseDto(foundPizza)).thenReturn(foundPizzaResponse);

        PizzaBaseResponseDto pizzaBaseResponseDto = pizzaService.findPizzaByName("pepperoni");

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(pizzaBaseResponseDto);
            Assertions.assertEquals("pepperoni", pizzaBaseResponseDto.name());
        });
    }
}