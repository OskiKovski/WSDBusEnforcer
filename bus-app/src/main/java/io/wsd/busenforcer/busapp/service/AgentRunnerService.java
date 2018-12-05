package io.wsd.busenforcer.busapp.service;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.wsd.busenforcer.agents.bus.model.BusInfo;
import io.wsd.busenforcer.agents.bus.o2a.RaiseEventO2A;
import io.wsd.busenforcer.agents.bus.o2a.UpdateBusInfoO2A;
import io.wsd.busenforcer.agents.bus.o2a.UpdateLocationO2A;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;
import io.wsd.busenforcer.busapp.agent.AgentRunner;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

@Service
public class AgentRunnerService {

    public static final String FAILED = "Failed!";
    Logger logger = LoggerFactory.getLogger(AgentRunnerService.class);

    @Autowired
    AgentRunner agentRunner;

    public String updateLocation(@NotNull Double lat, @NotNull Double lon) {
        final UpdateLocationO2A command = new UpdateLocationO2A(new Location(lat, lon));
        return sendO2ACommand(command) ? "Location updated." : FAILED;
    }


    public String updateBusInfo(@NotNull String line, @NotNull String brigade) {
        return sendO2ACommand(new UpdateBusInfoO2A(new BusInfo(line, brigade))) ? "Bus info changed." : FAILED;
    }

    public String raiseEvent() {
        return sendO2ACommand(new RaiseEventO2A()) ? "Event raised." : FAILED;
    }
    
    private boolean sendO2ACommand(@NotNull O2ACommand command) {
        Optional<AgentController> agent = agentRunner.getAgent();
        if(agent.isPresent()) {
            try {
                agent.get().putO2AObject(command, AgentController.ASYNC);
                return true;
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
