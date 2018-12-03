package io.wsd.busenforcer.busapp.api.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bus")
public class BusSimulationConsoleController {

    @Value("${spring.application.name}")
    String appName;

    @Value("${bus.line}")
    private String line;

    @Value("${bus.brigade}")
    private String brigade;

    @GetMapping("/console")
    public String console(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("line", line);
        model.addAttribute("brigade", brigade);
        return "console";
    }
}
