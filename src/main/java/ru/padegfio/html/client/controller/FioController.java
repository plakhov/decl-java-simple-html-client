package ru.padegfio.html.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.padegfio.html.client.service.PadegService;

@Controller
public class FioController {

    private final PadegService padegService;

    public FioController(PadegService padegService) {
        this.padegService = padegService;
    }

    @GetMapping("/index")
    public String showIndex(Model model, @RequestParam(value = "fio", required = false) String fio) {
        model.addAttribute("decls", padegService.getDeclination(fio));
        return "index";
    }

}
