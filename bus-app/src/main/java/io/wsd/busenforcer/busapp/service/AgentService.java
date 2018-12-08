package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.bus.o2a.RaiseEventO2A;
import io.wsd.busenforcer.agents.bus.o2a.UpdateBrigadeInfoO2A;
import io.wsd.busenforcer.agents.bus.o2a.UpdateLocationO2A;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.bus.integration.spring.BusAgentRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class AgentService {

    private static final String FAILED = "Failed!";
    private static final String SUCCESS = "Success.";

    Logger logger = LoggerFactory.getLogger(AgentService.class);

    @Autowired
    BusAgentRunner busAgentRunner;

    @EventListener(ApplicationReadyEvent.class)
    public void startAgent() {
        busAgentRunner.runAgent();
    }

    public String updateLocation(@NotNull Double lat, @NotNull Double lon) {
        final UpdateLocationO2A command = new UpdateLocationO2A(new Location(lat, lon));
        return busAgentRunner.sendO2ACommand(command) ? SUCCESS : FAILED;
    }

    public String updateBrigadeInfo(@NotNull String line, @NotNull String brigade) {
        return busAgentRunner.sendO2ACommand(new UpdateBrigadeInfoO2A(line, brigade)) ? SUCCESS : FAILED;
    }

    public String raiseEvent() {
        return busAgentRunner.sendO2ACommand(new RaiseEventO2A()) ? SUCCESS : FAILED;
    }

    public BusState getBusState() {
        return busAgentRunner.viewState();
    }
}
