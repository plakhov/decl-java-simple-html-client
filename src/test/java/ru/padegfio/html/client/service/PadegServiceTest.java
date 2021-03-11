package ru.padegfio.html.client.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.padegfio.html.client.dto.PadegDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PadegServiceTest {

    @Autowired
    PadegService padegService;

    @Test
    void simpleTest(){
        List<PadegDto> padegDtos = padegService.getDeclination("Пушкин Александр Сергеевич");
        assertThat(padegDtos).isNotEmpty().size().isEqualTo(6);
    }

}