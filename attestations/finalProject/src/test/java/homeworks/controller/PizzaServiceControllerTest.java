package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.*;
import homeworks.model.PizzaBase;
import homeworks.model.PizzaOrder;
import homeworks.service.PizzaService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PizzaServiceController.class)
class PizzaServiceControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PizzaService pizzaService;

    @Test
    void getPersons() throws Exception{
        List<PersonResponseDto> mockedPersonResponse = List.of(new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of()),
                new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of()));
        Mockito.when(pizzaService.getAllPersons()).thenReturn(mockedPersonResponse);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/person/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
    }

    @Test
    void getPersonById() throws Exception {
        PersonResponseDto mockFoundPerson = new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of());
        Mockito.when(pizzaService.findPersonById(2L)).thenReturn(mockFoundPerson);
        Mockito.when(pizzaService.findPersonById(50L)).thenThrow(NoSuchElementException.class);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/person/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fio").value("Стеклов Иван Петрович"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pizzaOrderList").value(new ArrayList<PizzaOrder>()));

        Assertions.assertThrows(ServletException.class, () -> mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/PizzaService/person/50")
                .accept(MediaType.APPLICATION_JSON)));
    }

    @Test
    void addPerson() throws Exception {
        PersonRequestDto mockInputPerson = new PersonRequestDto(null, "Петров Иван Петрович", List.of());
        PersonResponseDto mockSavedPerson = new PersonResponseDto(3L, "Петров Иван Петрович", List.of());
        Mockito.when(pizzaService.addPerson(mockInputPerson)).thenReturn(mockSavedPerson);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/PizzaService/person/add")
                        .content(mapper.writeValueAsBytes(mockInputPerson))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3L));
    }

    @Test
    void removePerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/PizzaService/person/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPizzas() throws Exception {
        List<PizzaBaseResponseDto> mockedPizzaResponse = List.of(new PizzaBaseResponseDto(1L, "pepperoni", 250),
                new PizzaBaseResponseDto(2L, "hawaiian", 400));
        Mockito.when(pizzaService.getAllPizzaBases()).thenReturn(mockedPizzaResponse);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/pizza/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
    }

    @Test
    void getPizzaById() throws Exception {
        PizzaBaseResponseDto mockFoundPizza = new PizzaBaseResponseDto(2L, "hawaiian", 400);
        Mockito.when(pizzaService.findPizzaBaseById(2L)).thenReturn(mockFoundPizza);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/pizza/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("hawaiian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.baseCost").value(400));
    }

    @Test
    void addPizza() throws Exception {
        PizzaBaseRequestDto mockInputPizza = new PizzaBaseRequestDto(null, "pepperoni", 300);
        PizzaBaseResponseDto mockSavedPizza = new PizzaBaseResponseDto(3L, "pepperoni", 300);
        Mockito.when(pizzaService.addPizzaBase(mockInputPizza)).thenReturn(mockSavedPizza);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/PizzaService/pizza/add")
                        .content(mapper.writeValueAsBytes(mockInputPizza))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3L));
    }

    @Test
    void getOrders() throws Exception {
        List<PizzaOrderResponseDto> mockedOrderResponse = List.of(new PizzaOrderResponseDto(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200))));
        Mockito.when(pizzaService.getAllPizzaOrders()).thenReturn(mockedOrderResponse);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/order/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L));
    }

    @Test
    void getOrderById() throws Exception {
        PizzaOrderResponseDto mockFoundOrder = new PizzaOrderResponseDto(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));

        Mockito.when(pizzaService.findPizzaOrderById(1L)).thenReturn(mockFoundOrder);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/order/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(760));
    }

    @Test
    void removeOrder() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/PizzaService/order/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPersonByFio() throws Exception {
        PersonResponseDto mockFoundPerson = new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of());
        Mockito.when(pizzaService.findPersonByFio("Стеклов Иван Петрович")).thenReturn(mockFoundPerson);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/person/fio/Стеклов Иван Петрович")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fio").value("Стеклов Иван Петрович"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pizzaOrderList").value(new ArrayList<PizzaOrder>()));
    }

    @Test
    void addOrderForPerson() throws Exception {
        PizzaOrderRequestDto mockRequestedOrder = new PizzaOrderRequestDto(null,List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PersonResponseDto mockOrderedPerson = new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of(new PizzaOrder(1L,760,List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)))));

        Mockito.when(pizzaService.addOrderForPersonById(eq(2L), any(PizzaOrderRequestDto.class))).thenReturn(mockOrderedPerson);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/PizzaService/person/2/addorder")
                        .content(mapper.writeValueAsBytes(mockRequestedOrder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pizzaOrderList[0].cost").value(760));

    }

    @Test
    void addOrderForPersonByName() throws Exception {
        PizzaOrderRequestDto mockRequestedOrder = new PizzaOrderRequestDto(null,List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PersonResponseDto mockOrderedPerson = new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of(new PizzaOrder(1L,760,List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)))));

        Mockito.when(pizzaService.addOrderForPersonByFio(eq("Стеклов Иван Петрович"), any(PizzaOrderRequestDto.class))).thenReturn(mockOrderedPerson);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/PizzaService/person/fio/Стеклов Иван Петрович/addorder")
                        .content(mapper.writeValueAsBytes(mockRequestedOrder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pizzaOrderList[0].cost").value(760));
    }
}