package ru.padegfio.html.client.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.padegfio.html.client.dto.PadegDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PadegServiceImpl implements PadegService {

    private final PadegClient padegClient;

    public PadegServiceImpl(PadegClient padegClient) {
        this.padegClient = padegClient;
    }

    @Override
    public List<PadegDto> getDeclination(String fio) {
        if (!StringUtils.hasText(fio)) {
            return Collections.emptyList();
        }
        final int quantityCase = 6;
        List<PadegDto> result = new ArrayList<>();
        for (int i = 0; i < quantityCase; i++) {
            result.add(new PadegDto(padegClient.getDecl(fio,i+1).getResult().get(0)));
        }
        return result;
    }
}
