package homeworks.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;
import homeworks.service.TVService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TVController.class)
class TVControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private TVService tvService;

    @Test
    void getTVs() throws Exception {
        List<TVResponse> mockedTVResponse = List.of(new TVResponse(1L, "Samsung", 15, true, true, 15, 50),
                (new TVResponse(2L, "Haier", 24, false, true, 99, 20)));
        Mockito.when(tvService.getTVs()).thenReturn(mockedTVResponse);
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/tv/alltv")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
    }

    @Test
    void addTV() throws Exception {
        TVRequest mockInputTV = new TVRequest(null, "Samsung", 15, true, true, 15, 50);
        TVResponse mockSavedTV = new TVResponse(1L, "Samsung", 15, true, true, 15, 50);
        Mockito.when(tvService.addTV(mockInputTV)).thenReturn(mockSavedTV);
        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/tv/addtv")
                .content(mapper.writeValueAsBytes(mockInputTV))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void getById() throws Exception {
        TVResponse mockFoundTV = new TVResponse(1L, "Samsung", 15, true, true, 15, 50);
        Mockito.when(tvService.findById(1L)).thenReturn(mockFoundTV);
        Mockito.when(tvService.findById(50L)).thenThrow(NoSuchElementException.class);

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
                        .delete("/api/v1/tv/1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeTV()  throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/tv/deletealltv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void changeState() throws Exception {
        TVResponse mockChangedTV = new TVResponse(1L, "Samsung", 15, true, false, 15, 50);
        Mockito.when(tvService.changeState(1L)).thenReturn(mockChangedTV);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/changestate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isEnabled").value(false));
    }

    @Test
    void allowedVolumeTV() throws Exception {
        List<TVResponse> mockSilentTVResponse = List.of(new TVResponse(2L, "Haier", 24, false, true, 99, 20));
        Mockito.when(tvService.getSilentTV(30)).thenReturn(mockSilentTVResponse);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tv/volume/30")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].volume").value(20));
    }

    @Test
    void nextChannel() throws Exception {
        TVResponse mockChangedTV = new TVResponse(1L, "Samsung", 15, true, true, 16, 50);
        Mockito.when(tvService.nextChannel(1L)).thenReturn(mockChangedTV);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/nextchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(16));

    }

    @Test
    void prevChannel() throws Exception {
        TVResponse mockChangedTV = new TVResponse(1L, "Samsung", 15, true, true, 14, 50);
        Mockito.when(tvService.prevChannel(1L)).thenReturn(mockChangedTV);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/prevchannel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(14));
    }

    @Test
    void goToChannel() throws Exception {
        TVResponse mockChangedTV = new TVResponse(1L, "Samsung", 15, true, true, 50, 50);
        Mockito.when(tvService.goToChannel(1L, 50)).thenReturn(mockChangedTV);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tv/1/channel/50")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currChannel").value(50));
    }
}