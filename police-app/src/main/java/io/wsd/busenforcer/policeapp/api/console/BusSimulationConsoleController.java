package io.wsd.busenforcer.policeapp.api.console;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.policeapp.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bus")
public class BusSimulationConsoleController {

    @Autowired
    AgentService agentService;

    @Value("${agent.bus.number}")
    String number;



    @GetMapping("/console")
    public String console(Model model) {
        model.addAttribute("number", number);
        BusState busState = agentService.getBusState();
        model.addAttribute("line", busState.getLine());
        model.addAttribute("brigade", busState.getBrigade());
        model.addAttribute("lat", busState.getLocation().getLat());
        model.addAttribute("lon", busState.getLocation().getLon());
        return "console";
    }
}
