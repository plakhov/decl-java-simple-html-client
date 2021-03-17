package ru.padegfio.html.client.service;

import ru.padegfio.html.client.dto.DeclinationType;
import ru.padegfio.html.client.dto.PadegDto;

import java.util.List;
import java.util.Map;

public interface PadegService {
    List<PadegDto> getDeclination(String fio);

    List<Map<String, List<PadegDto>>> getDeclinationFromFile(String pathToFile, DeclinationType declinationType, Integer quantityRepeat);
}
