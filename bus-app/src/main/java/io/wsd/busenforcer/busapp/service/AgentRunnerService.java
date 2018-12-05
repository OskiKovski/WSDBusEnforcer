package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.bus.o2a.RaiseEventO2A;
import io.wsd.busenforcer.agents.bus.o2a.UpdateBrigadeInfoO2A;
import io.wsd.busenforcer.agents.bus.o2a.UpdateLocationO2A;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;
import io.wsd.busenforcer.busapp.agent.AgentRunner;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AgentRunnerService {

    private static final String FAILED = "Failed!";
    private static final String SUCCESS = "Success.";

    Logger logger = LoggerFactory.getLogger(AgentRunnerService.class);

    @Autowired
    AgentRunner agentRunner;

    public String updateLocation(@NotNull Double lat, @NotNull Double lon) {
        final UpdateLocationO2A command = new UpdateLocationO2A(new Location(lat, lon));
        return sendO2ACommand(command) ? SUCCESS : FAILED;
    }

    public String updateBrigadeInfo(@NotNull String line, @NotNull String brigade) {
        return sendO2ACommand(new UpdateBrigadeInfoO2A(line, brigade)) ? SUCCESS : FAILED;
    }

    public String raiseEvent() {
        return sendO2ACommand(new RaiseEventO2A()) ? SUCCESS : FAILED;
    }

    private boolean sendO2ACommand(@NotNull O2ACommand command) {
        Optional<AgentController> agentController = agentRunner.getAgentController();
        if (agentController.isPresent()) {
            try {
                agentController.get().putO2AObject(command, AgentController.ASYNC);
                return true;
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public BusState getBusState() {
        return agentRunner.viewBusState();
    }
}
