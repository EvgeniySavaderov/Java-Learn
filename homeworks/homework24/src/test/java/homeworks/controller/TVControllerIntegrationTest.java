package homeworks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;
import homeworks.service.TVService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/test-tv-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Transactional
@Testcontainers
public class TVControllerIntegrationTest {
    @Container
    @ServiceConnection
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Autowired
    private TVService tvService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void getTVs() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tv/alltv"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(5));
    }

    @Test
    void addTV() throws Exception {
        TVRequest inputTV = new TVRequest(null, "Samsung", 15, true, true, 15, 50);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/addtv")
                        .content(mapper.writeValueAsBytes(inputTV))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(6L));
    }

    @Test
    void getById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tv/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Samsung"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isEnabled").value(true));

        Assertions.assertThrows(ServletException.class, () -> mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/tv/50")
                .accept(MediaType.APPLICATION_JSON)));
    }

    @Test
    void deleteAllTv() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/tv/deletealltv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tv/alltv"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }

    @Test
    void removeTV()  throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/tv/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tv/alltv"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4));
    }

    @Test
    void changeState() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/changestate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isEnabled").value(false));
    }

    @Test
    void allowedVolumeTV() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tv/volume/45")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].volume").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].volume").value(40));
    }

    @Test
    void nextChannel() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/nextchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(16));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/2/nextchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(0));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/4/nextchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(27));
    }

    @Test
    void prevChannel() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/prevchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(14));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/5/prevchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(99));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/4/prevchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(27));
    }
}
