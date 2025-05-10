package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.UserRequest;
import homeworks.dto.UserResponse;
import homeworks.service.UserService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private UserService userService;

    @Test
    public void getUsersTest() throws Exception {
        List<UserResponse> mockUserResponse = List.of(
                new UserResponse(1L, "Петр", LocalDate.of(1998, 7, 5)),
                new UserResponse(2L, "Григорий", LocalDate.of(1999, 11, 25))
        );
        Mockito.when(userService.getUsers()).thenReturn(mockUserResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/allusers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
    }

    @Test
    public void addUserTest() throws Exception {
        UserRequest mockInputUser = new UserRequest(null, "Наташа", LocalDate.of(2001, 2, 12));
        UserResponse mockSavedUser = new UserResponse(1L, "Наташа", LocalDate.of(2001, 2, 12));
        Mockito.when(userService.addUser(Mockito.any(UserRequest.class))).thenReturn(mockSavedUser);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/users/adduser")
                .content(mapper.writeValueAsBytes(mockInputUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    public void deleteAllUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/deleteallusers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("all users deleted"));
    }

    @Test
    public void removeUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/users/remove/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getByIdTest() throws Exception {
        UserResponse mockFoundUser = new UserResponse(1L, "Наташа", LocalDate.of(2001, 2, 12));
        Mockito.when(userService.findById(1L)).thenReturn(mockFoundUser);
        Mockito.when(userService.findById(50L)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Наташа"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate").value("2001-02-12"));

        Assertions.assertThrows(ServletException.class, () -> mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/50")
                .accept(MediaType.APPLICATION_JSON)));
    }
}