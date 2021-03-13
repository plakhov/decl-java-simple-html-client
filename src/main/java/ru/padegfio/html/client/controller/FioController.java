package ru.padegfio.html.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.padegfio.html.client.dto.DeclinationType;
import ru.padegfio.html.client.dto.PadegDto;
import ru.padegfio.html.client.service.PadegService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FioController {

    private final PadegService padegService;

    public FioController(PadegService padegService) {
        this.padegService = padegService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping("/fio")
    public String declFio(Model model, @RequestParam(value = "path", required = false) String pathToFile) {
        Map<String, List<PadegDto>> declination = padegService.getDeclinationFromFile(pathToFile, DeclinationType.FIO);
        List<PadegDto> result = new ArrayList<>();
        for (Map.Entry<String, List<PadegDto>> entry : declination.entrySet()) {
            result.addAll(entry.getValue());
            result.add(new PadegDto(""));
        }
        model.addAttribute("decls", result);
        model.addAttribute("path", pathToFile);
        return "fio";
    }

    @GetMapping("/office")
    public String declOffice(Model model, @RequestParam(value = "path", required = false) String pathToFile) {
        Map<String, List<PadegDto>> declination = padegService.getDeclinationFromFile(pathToFile, DeclinationType.DEPARTMENT);
        List<PadegDto> result = new ArrayList<>();
        for (Map.Entry<String, List<PadegDto>> entry : declination.entrySet()) {
            result.addAll(entry.getValue());
            result.add(new PadegDto(""));
        }
        model.addAttribute("decls", result);
        model.addAttribute("path", pathToFile);
        return "office";
    }

    @GetMapping("/prof")
    public String declProf(Model model, @RequestParam(value = "path", required = false) String pathToFile) {
        Map<String, List<PadegDto>> declination = padegService.getDeclinationFromFile(pathToFile, DeclinationType.PROFESSION);
        List<PadegDto> result = new ArrayList<>();
        for (Map.Entry<String, List<PadegDto>> entry : declination.entrySet()) {
            result.addAll(entry.getValue());
            result.add(new PadegDto(""));
        }
        model.addAttribute("decls", result);
        model.addAttribute("path", pathToFile);
        return "prof";
    }

}
