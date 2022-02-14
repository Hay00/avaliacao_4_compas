package com.compass.microservice.partidos;

import com.compass.microservice.partidos.controller.PartidoController;
import com.compass.microservice.partidos.repository.PartidoRepository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PartidoControllerTests {

    @Mock
    private PartidoRepository partidoRepository;

    @InjectMocks
    private PartidoController partidoController;

    @Test
    public void canCreatePartido() throws Exception {
        assertThat(partidoController).isNotNull();
    }
}
