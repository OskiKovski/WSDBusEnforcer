package io.wsd.busenforcer.policeapp.service;

import io.wsd.busenforcer.agents.police.integration.spring.PoliceAgentRunner;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentService {

    final PoliceAgentRunner policeAgentRunner;

    @EventListener(ApplicationReadyEvent.class)
    public void startAgent() {
        policeAgentRunner.runAgent();
    }

    public PoliceState getPoliceState() {
        return policeAgentRunner.viewState();
    }
}
