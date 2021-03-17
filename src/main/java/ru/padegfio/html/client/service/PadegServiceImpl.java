package ru.padegfio.html.client.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.padegfio.html.client.dto.DeclinationType;
import ru.padegfio.html.client.dto.PadegDto;
import ru.padegfio.html.client.dto.PadegResponseDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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
            result.add(new PadegDto(padegClient.getFioDecl(fio, i + 1).getResult().get(0)));
        }
        return result;
    }

    @Override
    public List<Map<String, List<PadegDto>>> getDeclinationFromFile(String pathToFile, DeclinationType declinationType, Integer quantityRepeat) {
        BufferedReader reader;
        if (!StringUtils.hasText(pathToFile)) {
            return Collections.emptyList();
        }
        List<Map<String, List<PadegDto>>> result = new ArrayList<>();
        try {
            if (!pathToFile.endsWith(".txt")) {
                if (!pathToFile.endsWith(File.separator)) {
                    pathToFile += File.separator;
                }
                pathToFile += declinationType.getFileName();
            }
            reader = new BufferedReader(new FileReader(pathToFile));
            if (quantityRepeat == null || quantityRepeat <= 0) {
                quantityRepeat = 1;
            }
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (int i = 0; i < quantityRepeat; i++) {
                result.add(getDeclinationFromFile(lines, declinationType));
            }
            return result;
        } catch (IOException e) {
            return result;
        }
    }

    private Map<String, List<PadegDto>> getDeclinationFromFile(List<String> lines, DeclinationType declinationType) {
        Map<String, List<PadegDto>> result = new LinkedHashMap<>();
        lines.forEach(line -> {
            List<PadegDto> decls = new ArrayList<>(6);
            switch (declinationType) {
                case FIO:
                    decls.addAll(getDeclination(line, padegClient::getFioDecl));
                    break;
                case DEPARTMENT:
                    decls.addAll(getDeclination(line, padegClient::getOfficeDecl));
                    break;
                case PROFESSION:
                    decls.addAll(getDeclination(line, padegClient::getProfessionDecl));
                    break;
            }
            result.put(line, decls);
        });
        return result;
    }

    private List<PadegDto> getDeclination(String decl, BiFunction<String, Integer, PadegResponseDto> declFunction) {
        List<PadegDto> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            result.add(new PadegDto(declFunction.apply(decl, i + 1).getResult().get(0)));
        }
        return result;
    }
}
