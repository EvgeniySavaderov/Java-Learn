package homeworks.service.impl;

import homeworks.dto.*;
import homeworks.mapper.*;
import homeworks.model.Person;
import homeworks.model.PizzaBase;
import homeworks.model.PizzaOrder;
import homeworks.repository.PersonRepository;
import homeworks.repository.PizzaBaseRepository;
import homeworks.repository.PizzaOrderRepository;
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
    private PersonRepository personRepository;
    @MockitoBean
    private PersonRequestMapper personRequestMapper;
    @MockitoBean
    private PersonResponseMapper personResponseMapper;
    @MockitoBean
    private PizzaBaseRepository pizzaBaseRepository;
    @MockitoBean
    private PizzaBaseRequestMapper pizzaBaseRequestMapper;
    @MockitoBean
    private PizzaBaseResponseMapper pizzaBaseResponseMapper;
    @MockitoBean
    private PizzaOrderRepository pizzaOrderRepository;
    @MockitoBean
    private PizzaOrderRequestMapper pizzaOrderRequestMapper;
    @MockitoBean
    private PizzaOrderResponseMapper pizzaOrderResponseMapper;

    @Test
    void getAllPersons() {
        List<Person> personEntities = new ArrayList<>();
        personEntities.add(new Person(1L, "Иванов Петр Алексеевич", List.of()));
        personEntities.add(new Person(2L, "Стеклов Иван Петрович", List.of()));
        PersonResponseDto personResponseDto = new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of());
        Mockito.when(personRepository.findAll()).thenReturn(personEntities);
        Mockito.when(personResponseMapper.toPersonResponseDto(any(Person.class))).thenReturn(personResponseDto);

        List<PersonResponseDto> personResponseList = pizzaService.getAllPersons();

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseList);
            Assertions.assertEquals(2, personResponseList.size());
        });
    }
    @Test
    void findPersonById() {
        Person foundPerson = new Person(1L, "Иванов Петр Алексеевич", List.of());
        PersonResponseDto foundPersonResponse = new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of());

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(foundPerson));
        Mockito.when(personResponseMapper.toPersonResponseDto(foundPerson)).thenReturn(foundPersonResponse);

        PersonResponseDto personResponseDto = pizzaService.findPersonById(1L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseDto);
            Assertions.assertEquals(1L, personResponseDto.id());
        });
    }

    @Test
    void addPerson() {
        PersonRequestDto addPersonRequest = new PersonRequestDto(null, "Кузнецов Иван Алексеевич", List.of());
        Person person = new Person(null, "Кузнецов Иван Алексеевич", List.of());
        Person addedPerson = new Person(3L, "Кузнецов Иван Алексеевич", List.of());
        PersonResponseDto addedpersonResponse = new PersonResponseDto(3L, "Кузнецов Иван Алексеевич", List.of());

        Mockito.when(personRequestMapper.toEntity(addPersonRequest)).thenReturn(person);
        Mockito.when(personRepository.save(person)).thenReturn(addedPerson);
        Mockito.when(personResponseMapper.toPersonResponseDto(addedPerson)).thenReturn(addedpersonResponse);

        PersonResponseDto personResponseDto = pizzaService.addPerson(addPersonRequest);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseDto);
            Assertions.assertEquals(3L, personResponseDto.id());
        });
    }
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

        List<PizzaOrderResponseDto> allOrders = pizzaService.getAllPizzaOrders();

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

        PizzaOrderResponseDto pizzaOrderResponseDto = pizzaService.findPizzaOrderById(1L);

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

        PizzaOrderResponseDto pizzaOrderResponseDto = pizzaService.addPizzaOrder(addOrderRequest);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(pizzaOrderResponseDto);
            Assertions.assertEquals(2L, pizzaOrderResponseDto.id());
        });
    }

    @Test
    void findPersonByFio() {
        Person foundPerson = new Person(1L, "Иванов Петр Алексеевич", List.of());
        PersonResponseDto foundPersonResponse = new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of());

        Mockito.when(personRepository.findPersonByFio("Иванов Петр Алексеевич")).thenReturn(List.of(foundPerson));
        Mockito.when(personResponseMapper.toPersonResponseDto(foundPerson)).thenReturn(foundPersonResponse);

        PersonResponseDto personResponseDto = pizzaService.findPersonByFio("Иванов Петр Алексеевич");

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(personResponseDto);
            Assertions.assertEquals("Иванов Петр Алексеевич", personResponseDto.fio());
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