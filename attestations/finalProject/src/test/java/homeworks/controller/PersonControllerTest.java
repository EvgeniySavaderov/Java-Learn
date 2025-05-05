package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.PersonRequestDto;
import homeworks.dto.PersonResponseDto;
import homeworks.model.PizzaOrder;
import homeworks.service.PersonService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonService personService;

    @Test
    void getPersons() throws Exception{
        List<PersonResponseDto> mockedPersonResponse = List.of(new PersonResponseDto(1L, "Иванов Петр Алексеевич", List.of()),
                new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of()));
        Mockito.when(personService.getAllPersons()).thenReturn(mockedPersonResponse);
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
        Mockito.when(personService.findPersonById(2L)).thenReturn(mockFoundPerson);
        Mockito.when(personService.findPersonById(50L)).thenThrow(NoSuchElementException.class);

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
    void getPersonByFio() throws Exception {
        PersonResponseDto mockFoundPerson = new PersonResponseDto(2L, "Стеклов Иван Петрович", List.of());
        Mockito.when(personService.findPersonByFio("Стеклов Иван Петрович")).thenReturn(mockFoundPerson);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/PizzaService/person/fio/Стеклов Иван Петрович")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fio").value("Стеклов Иван Петрович"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pizzaOrderList").value(new ArrayList<PizzaOrder>()));
    }

    @Test
    void addPerson() throws Exception {
        PersonRequestDto mockInputPerson = new PersonRequestDto(null, "Петров Иван Петрович", List.of());
        PersonResponseDto mockSavedPerson = new PersonResponseDto(3L, "Петров Иван Петрович", List.of());
        Mockito.when(personService.addPerson(mockInputPerson)).thenReturn(mockSavedPerson);
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
}