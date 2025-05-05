package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.PizzaBaseRequestDto;
import homeworks.dto.PizzaBaseResponseDto;
import homeworks.service.PizzaService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PizzaController.class)
class PizzaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PizzaService pizzaService;

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
}