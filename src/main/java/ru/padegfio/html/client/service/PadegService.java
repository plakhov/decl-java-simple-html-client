package ru.padegfio.html.client.service;

import ru.padegfio.html.client.dto.PadegDto;

import java.util.List;

public interface PadegService {
    List<PadegDto> getDeclination(String fio);
}
