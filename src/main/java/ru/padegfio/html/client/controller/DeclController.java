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
public class DeclController {

    private final PadegService padegService;

    public DeclController(PadegService padegService) {
        this.padegService = padegService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping("/fio")
    public String declFio(Model model, @RequestParam(value = "path", required = false) String pathToFile,
                          @RequestParam(value = "quantityRepeat", required = false) Integer quantityRepeat) {
        return declination(model, pathToFile, quantityRepeat, DeclinationType.FIO);
    }

    @GetMapping("/office")
    public String declOffice(Model model, @RequestParam(value = "path", required = false) String pathToFile,
                             @RequestParam(value = "quantityRepeat", required = false) Integer quantityRepeat) {
        return declination(model, pathToFile, quantityRepeat, DeclinationType.DEPARTMENT);
    }

    private String declination(Model model, String pathToFile, Integer quantityRepeat, DeclinationType declinationType) {
        List<Map<String, List<PadegDto>>> declination = padegService.getDeclinationFromFile(pathToFile, declinationType, quantityRepeat);
        List<PadegDto> result = new ArrayList<>();
        for (Map<String, List<PadegDto>> stringListMap : declination) {
            for (Map.Entry<String, List<PadegDto>> entry : stringListMap.entrySet()) {
                result.addAll(entry.getValue());
                result.add(new PadegDto(""));
            }
        }
        model.addAttribute("decls", result);
        model.addAttribute("path", pathToFile);
        return declinationType.getTemplateName();
    }

    @GetMapping("/prof")
    public String declProf(Model model, @RequestParam(value = "path", required = false) String pathToFile,
                           @RequestParam(value = "quantityRepeat", required = false) Integer quantityRepeat) {
        return declination(model, pathToFile, quantityRepeat, DeclinationType.FIO);
    }

}
