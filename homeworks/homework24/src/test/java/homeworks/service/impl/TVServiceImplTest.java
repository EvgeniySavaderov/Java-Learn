package homeworks.service.impl;

import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;
import homeworks.mapper.TVRequestMapper;
import homeworks.mapper.TVResponseMapper;
import homeworks.model.TV;
import homeworks.repository.TVRepository;
import homeworks.service.TVService;
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

@SpringBootTest(classes = TVServiceImpl.class)
class TVServiceImplTest {
    @Autowired
    private TVService tvService;
    @MockitoBean
    private TVRepository tvRepository;
    @MockitoBean
    private TVRequestMapper tvRequestMapper;
    @MockitoBean
    TVResponseMapper tvResponseMapper;

    @Test
    void getTVs() {
        List<TV> tvEntities = new ArrayList<>();
        tvEntities.add(new TV(1L, "Samsung", 15, true, true, 15, 5));
        tvEntities.add(new TV(2L, "Haier", 24, false, true, 99, 20));
        List<TVResponse> tvResponses = new ArrayList<>();
        TVResponse tvResponse = new TVResponse(1L, "Samsung", 15, true, true, 15, 5);

        Mockito.when(tvRepository.findAll()).thenReturn(tvEntities);
        Mockito.when(tvResponseMapper.toTVResponse(any(TV.class))).thenReturn(tvResponse);

        List<TVResponse> tvResponseList = tvService.getTVs();

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponseList);
            Assertions.assertEquals(2, tvResponseList.size());
        });
    }

    @Test
    void addTV() {
        TVRequest addedTVRequest = new TVRequest(null,"Haier", 24, false, true, 99, 20);
        TV tv = new TV(null,"Haier", 24, false, true, 99, 20);
        TV addedTV = new TV(2L,"Haier", 24, false, true, 99, 20);
        TVResponse addedTVResponse = new TVResponse(2L, "Haier", 24, false, true, 99, 20);

        Mockito.when(tvRequestMapper.toEntity(addedTVRequest)).thenReturn(tv);
        Mockito.when(tvRepository.save(tv)).thenReturn(addedTV);
        Mockito.when(tvResponseMapper.toTVResponse(addedTV)).thenReturn(addedTVResponse);

        TVResponse tvResponse = tvService.addTV(addedTVRequest);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponse);
            Assertions.assertEquals(2L, tvResponse.id());
        });
    }

    @Test
    void findById() {
        TV foundTV = new TV(2L,"Haier", 24, false, true, 99, 20);
        TVResponse foundTVResponse = new TVResponse(2L, "Haier", 24, false, true, 99, 20);

        Mockito.when(tvRepository.findById(2L)).thenReturn(Optional.of(foundTV));
        Mockito.when(tvResponseMapper.toTVResponse(foundTV)).thenReturn(foundTVResponse);

        TVResponse tvResponse = tvService.findById(2L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponse);
            Assertions.assertEquals(2L, tvResponse.id());
        });
    }

    @Test
    void changeState() {
        TV changedTV = new TV(2L,"Haier", 24, false, true, 99, 20);
        TVResponse changedTVResponse = new TVResponse(2L, "Haier", 24, false, false, 99, 20);

        Mockito.when(tvRepository.findById(2L)).thenReturn(Optional.of(changedTV));
        Mockito.when(tvResponseMapper.toTVResponse(changedTV)).thenReturn(changedTVResponse);

        TVResponse tvResponse = tvService.changeState(2L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponse);
            Assertions.assertEquals(2L, tvResponse.id());
        });
    }

    @Test
    void getSilentTV() {
        TV tv = new TV(1L, "Samsung", 15, true, true, 15, 5);
        TV tv1 = new TV(2L, "Haier", 24, false, true, 99, 20);
        List<TV> silentTVEntities = new ArrayList<>();
        silentTVEntities.add(tv);
        silentTVEntities.add(tv1);
        TVResponse tvResponse = new TVResponse(1L, "Samsung", 15, true, true, 15, 5);
        TVResponse tvResponse1 = new TVResponse(2L, "Haier", 24, false, true, 99, 20);

        Mockito.when(tvRepository.getSilentTV(30)).thenReturn(silentTVEntities);
        Mockito.when(tvResponseMapper.toTVResponse(tv)).thenReturn(tvResponse);
        Mockito.when(tvResponseMapper.toTVResponse(tv1)).thenReturn(tvResponse1);

        List<TVResponse> tvResponseList = tvService.getSilentTV(30);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponseList);
            Assertions.assertEquals(2, tvResponseList.size());
            Assertions.assertEquals(5, tvResponseList.get(0).volume());
            Assertions.assertEquals(20, tvResponseList.get(1).volume());
        });
    }

    @Test
    void nextChannel() {
        TV changedTV = new TV(2L,"Haier", 24, false, true, 99, 20);
        TVResponse changedTVResponse = new TVResponse(2L, "Haier", 24, false, true, 0, 20);

        Mockito.when(tvRepository.findById(2L)).thenReturn(Optional.of(changedTV));
        Mockito.when(tvResponseMapper.toTVResponse(changedTV)).thenReturn(changedTVResponse);

        TVResponse tvResponse = tvService.nextChannel(2L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponse);
            Assertions.assertEquals(2L, tvResponse.id());
        });
    }

    @Test
    void prevChannel() {
        TV changedTV = new TV(2L,"Haier", 24, false, true, 99, 20);
        TVResponse changedTVResponse = new TVResponse(2L, "Haier", 24, false, true, 98, 20);

        Mockito.when(tvRepository.findById(2L)).thenReturn(Optional.of(changedTV));
        Mockito.when(tvResponseMapper.toTVResponse(changedTV)).thenReturn(changedTVResponse);

        TVResponse tvResponse = tvService.prevChannel(2L);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponse);
            Assertions.assertEquals(2L, tvResponse.id());
        });
    }

    @Test
    void goToChannel() {
        TV changedTV = new TV(2L,"Haier", 24, false, true, 99, 20);
        TVResponse changedTVResponse = new TVResponse(2L, "Haier", 24, false, true, 50, 20);

        Mockito.when(tvRepository.findById(2L)).thenReturn(Optional.of(changedTV));
        Mockito.when(tvResponseMapper.toTVResponse(changedTV)).thenReturn(changedTVResponse);

        TVResponse tvResponse = tvService.goToChannel(2L, 50);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(tvResponse);
            Assertions.assertEquals(2L, tvResponse.id());
        });
    }
}