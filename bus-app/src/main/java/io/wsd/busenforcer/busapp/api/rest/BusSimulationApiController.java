package io.wsd.busenforcer.busapp.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.wsd.busenforcer.busapp.api.rest.dto.BrigadeInfoDTO;
import io.wsd.busenforcer.busapp.service.AgentService;

@RestController
@RequestMapping(value = "/bus/api")
public class BusSimulationApiController {

    @Autowired
    AgentService agentService;

    @RequestMapping(value = "/raiseEvent", method = RequestMethod.POST)
    public String raiseEvent() {
        return agentService.raiseEvent();
    }

    @PostMapping(value = "/updateBrigadeInfo", consumes = "application/json")
    public String updateBrigadeInfo(@RequestBody BrigadeInfoDTO dto) {
        return agentService.updateBrigadeInfo(dto.getLine(), dto.getBrigade());
    }
}
