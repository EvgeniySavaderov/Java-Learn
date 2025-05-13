package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.PersonResponseDto;
import homeworks.dto.PizzaOrderRequestDto;
import homeworks.model.PizzaBase;
import homeworks.model.PizzaOrder;
import homeworks.service.PersonAddOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonAddOrderController.class)
class PersonAddOrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonAddOrderService pizzaAddOrderService;

    @Test
    void addOrderForPerson() throws Exception {
        PizzaOrderRequestDto mockRequestedOrder = new PizzaOrderRequestDto(null,List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)));
        PersonResponseDto mockOrderedPerson = new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of(new PizzaOrder(1L,760,List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200)))));

        Mockito.when(pizzaAddOrderService.addOrderForPersonById(eq(2L), any(PizzaOrderRequestDto.class))).thenReturn(mockOrderedPerson);

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

        Mockito.when(pizzaAddOrderService.addOrderForPersonByFio(eq("Стеклов Иван Петрович"), any(PizzaOrderRequestDto.class))).thenReturn(mockOrderedPerson);

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