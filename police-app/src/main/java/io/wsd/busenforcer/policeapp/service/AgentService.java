package io.wsd.busenforcer.policeapp.service;

import io.wsd.busenforcer.agents.police.integration.spring.PoliceAgentRunner;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    Logger logger = LoggerFactory.getLogger(AgentService.class);

    @Autowired
    PoliceAgentRunner policeAgentRunner;

    @EventListener(ApplicationReadyEvent.class)
    public void startAgent() {
        policeAgentRunner.runAgent();
    }

    public PoliceState getPoliceState() {
        return policeAgentRunner.viewState();
    }
}
