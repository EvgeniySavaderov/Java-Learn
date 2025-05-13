package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.PizzaOrderResponseDto;
import homeworks.model.PizzaBase;
import homeworks.service.OrderService;
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

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private OrderService orderService;

    @Test
    void getOrders() throws Exception {
        List<PizzaOrderResponseDto> mockedOrderResponse = List.of(new PizzaOrderResponseDto(1L, 760, List.of("big", "standard"), List.of(1, 2), List.of(
                new PizzaBase(1L, "pepperoni", 300),
                new PizzaBase(2L, "pepperoni", 200))));
        Mockito.when(orderService.getAllPizzaOrders()).thenReturn(mockedOrderResponse);
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

        Mockito.when(orderService.findPizzaOrderById(1L)).thenReturn(mockFoundOrder);

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
}