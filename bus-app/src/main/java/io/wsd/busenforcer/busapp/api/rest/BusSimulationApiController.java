package io.wsd.busenforcer.busapp.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.wsd.busenforcer.busapp.api.rest.dto.BusInfoDTO;
import io.wsd.busenforcer.busapp.service.AgentRunnerService;

@RestController
@RequestMapping(value = "/bus/api")
public class BusSimulationApiController {

    @Autowired
    AgentRunnerService agentRunnerService;

    @RequestMapping(value = "/raiseEvent", method = RequestMethod.POST)
    public String raiseEvent() {
        return agentRunnerService.raiseEvent();
    }

    @PostMapping(value = "/updateBusInfo", consumes = "application/json")
    public String setBusInfo(@RequestBody BusInfoDTO busInfo) {
        return agentRunnerService.updateBusInfo(busInfo.getLine(), busInfo.getBrigade());
    }
}
