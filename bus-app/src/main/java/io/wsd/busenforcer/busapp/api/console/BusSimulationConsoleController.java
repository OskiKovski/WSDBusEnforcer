package io.wsd.busenforcer.busapp.api.console;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.busapp.api.dto.BrigadeInfoDTO;
import io.wsd.busenforcer.busapp.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bus/console")
public class BusSimulationConsoleController {

    public static final String REDIRECT_BUS_CONSOLE = "redirect:/bus/console";
    @Autowired
    AgentService agentService;

    @Value("${agent.bus.number}")
    String number;


    @GetMapping("")
    public String console(Model model) {
        model.addAttribute("number", number);
        BusState busState = agentService.getBusState();
        model.addAttribute("brigadeInfo", BrigadeInfoDTO.of(busState.getLine(), busState.getBrigade()));
        model.addAttribute("lat", busState.getLocation().getLat());
        model.addAttribute("lon", busState.getLocation().getLon());
        return "console";
    }

    @PostMapping("/updateBrigadeInfo")
    public String updateBrigadeInfo(@ModelAttribute BrigadeInfoDTO dto) {
        agentService.updateBrigadeInfo(dto.getLine(), dto.getBrigade());
        return REDIRECT_BUS_CONSOLE;
    }

    @PostMapping("/raiseEvent")
    public String raiseEvent() {
        agentService.raiseEvent();
        return REDIRECT_BUS_CONSOLE;
    }
}
