package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.agents.bus.o2a.UpdateLocationO2A;
import io.wsd.busenforcer.busapp.agent.AgentRunner;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AgentRunnerService {


    @Autowired
    AgentRunner agentRunner;

    public void updateLocation(@NotNull Double lat, @NotNull Double lon) {
        Optional<AgentController> agent = agentRunner.getAgent();
        if (agent.isPresent()) {
            try {
                agent.get().putO2AObject(new UpdateLocationO2A(lat, lon), AgentController.ASYNC);
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }
    }
}
