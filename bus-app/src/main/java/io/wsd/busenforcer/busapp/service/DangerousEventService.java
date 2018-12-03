package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.busapp.agent.AgentRunner;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DangerousEventService {

    Logger logger = LoggerFactory.getLogger(DangerousEventService.class);

    @Autowired
    AgentRunner agentRunner;

    public String raiseEvent() {
        logger.info("Event raised.");
        Optional<AgentController> agent = agentRunner.getAgent();
        if(agent.isPresent()) {
            try {
                agent.get().putO2AObject(BusAgent.O2A_RAISE_EVENT, AgentController.ASYNC);
                return "Event raised";
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }
        return "Failed!";
    }
}
