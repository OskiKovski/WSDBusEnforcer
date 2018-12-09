package io.wsd.busenforcer.policeapp.api.console;

import io.wsd.busenforcer.agents.police.model.PoliceState;
import io.wsd.busenforcer.policeapp.api.dto.LocationDTO;
import io.wsd.busenforcer.policeapp.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/police")
public class PoliceConsoleController {

    @Autowired
    AgentService agentService;


    @GetMapping("/console")
    public String console(Model model) {
        PoliceState policeState = agentService.getPoliceState();
        model.addAttribute("location",
                LocationDTO.of(policeState.getLocation().getLat(), policeState.getLocation().getLon()));
        model.addAttribute("unitId", policeState.getUnitId());
        return "console";
    }

    @PostMapping("/console/updateLocation")
    public String updateLocation(@ModelAttribute LocationDTO location) {
        agentService.updateLocation(location.getLat(), location.getLon());
        return "redirect:/police/console";
    }
}
