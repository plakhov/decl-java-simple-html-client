package ru.padegfio.html.client.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.padegfio.html.client.dto.DeclinationType;
import ru.padegfio.html.client.dto.PadegDto;
import ru.padegfio.html.client.dto.PadegResponseDto;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

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
    public Map<String, List<PadegDto>> getDeclinationFromFile(String pathToFile, DeclinationType declinationType) {
        FileReader fileReader;
        if (!StringUtils.hasText(pathToFile)) {
            return Collections.emptyMap();
        }
        try {
            if (!pathToFile.endsWith(".txt")) {
                if (!pathToFile.endsWith(File.separator)) {
                    pathToFile += File.separator;
                }
                pathToFile += declinationType.getFileName();
            }
            fileReader = new FileReader(pathToFile);
            return getDeclinationFromFile(fileReader, declinationType);
        } catch (FileNotFoundException e) {
            return Collections.emptyMap();
        }
    }

    private Map<String, List<PadegDto>> getDeclinationFromFile(FileReader fileReader, DeclinationType declinationType) {
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        String line;
        Map<String, List<PadegDto>> result = new LinkedHashMap<>();
        while (true) {
            try {
                if ((line = lineNumberReader.readLine()) == null) break;
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
            } catch (IOException ignored) {
            }
        }
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
