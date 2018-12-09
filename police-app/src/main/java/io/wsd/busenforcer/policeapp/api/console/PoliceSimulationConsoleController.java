package io.wsd.busenforcer.policeapp.api.console;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import io.wsd.busenforcer.policeapp.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bus")
public class PoliceSimulationConsoleController {

    @Autowired
    AgentService agentService;



    @GetMapping("/console")
    public String console(Model model) {
        PoliceState policeState = agentService.getPoliceState();
        model.addAttribute("id", policeState.getId());
        model.addAttribute("lat", policeState.getLocation().getLat());
        model.addAttribute("lon", policeState.getLocation().getLon());
        return "console";
    }
}
