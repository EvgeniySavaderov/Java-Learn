package homeworks.repository;

import homeworks.model.TV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Testcontainers
@Sql(scripts = "/test-tv-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Transactional
public class TVRepositoryTest {
    @Autowired
    private TVRepository tvRepository;

    @Container
    @ServiceConnection
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Test
    public void getSilentTV() {
        List<TV> silentTVs = tvRepository.getSilentTV(50);
        List<TV> expectedTVs = new ArrayList<>();
        expectedTVs.add(tvRepository.findById(2L).orElseThrow());
        expectedTVs.add(tvRepository.findById(3L).orElseThrow());
        expectedTVs.add(tvRepository.findById(1L).orElseThrow());
        Assertions.assertNotNull(expectedTVs);
        Assertions.assertEquals(expectedTVs, silentTVs);
    }

    @Test
    void tvChangeState() {
        tvRepository.tvChangeState(3L);
        tvRepository.tvChangeState(4L);
        Assertions.assertEquals(false, tvRepository.findById(3L).orElseThrow().getIsEnabled());
        Assertions.assertEquals(true, tvRepository.findById(4L).orElseThrow().getIsEnabled());
    }

    @Test
    void tvNextChannel() {
        tvRepository.tvNextChannel(2L);
        tvRepository.tvNextChannel(3L);
        tvRepository.tvNextChannel(4L);
        Assertions.assertEquals(0, tvRepository.findById(2L).orElseThrow().getCurrChannel());
        Assertions.assertEquals(18, tvRepository.findById(3L).orElseThrow().getCurrChannel());
        Assertions.assertEquals(27, tvRepository.findById(4L).orElseThrow().getCurrChannel());
    }

    @Test
    void tvPrevChannel() {
        tvRepository.tvPrevChannel(3L);
        tvRepository.tvPrevChannel(4L);
        tvRepository.tvPrevChannel(5L);
        Assertions.assertEquals(16, tvRepository.findById(3L).orElseThrow().getCurrChannel());
        Assertions.assertEquals(27, tvRepository.findById(4L).orElseThrow().getCurrChannel());
        Assertions.assertEquals(99, tvRepository.findById(5L).orElseThrow().getCurrChannel());
    }

    @Test
    void tvGoToChannel() {
        tvRepository.tvGoToChannel(5L, 50);
        tvRepository.tvGoToChannel(4L, 50);
        Assertions.assertEquals(50, tvRepository.findById(5L).orElseThrow().getCurrChannel());
        Assertions.assertEquals(27, tvRepository.findById(4L).orElseThrow().getCurrChannel());
    }
}