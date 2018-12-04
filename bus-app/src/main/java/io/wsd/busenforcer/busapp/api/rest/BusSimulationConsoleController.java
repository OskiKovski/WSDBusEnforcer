package io.wsd.busenforcer.busapp.api.rest;

import io.wsd.busenforcer.busapp.service.BusSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BusSimulationService busSimulationService;

    @GetMapping("/console")
    public String console(Model model) {
        model.addAttribute("appName", appName);
        return "console";
    }
}