package io.wsd.busenforcer.busapp.agent;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.integration.spring.SpringAgentRunner;
import io.wsd.busenforcer.agents.common.model.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// TODO: 06.12.2018 jkumor - probably should be in agents project as integration package
@Component
public class BusAgentRunner extends SpringAgentRunner<BusAgent> {

    @Value("${bus.number}")
    private String number;

    @Value("${bus.line}")
    private String line;

    @Value("${bus.brigade}")
    private String brigade;

    @Override
    protected String buildAgentNickname() {
        return "bus-agent-" + number;
    }

    @Override
    protected BusAgent createAgent() {
        BusState initialBusState = new BusState(line, brigade, new Location(.0, .0));
        return new BusAgent(initialBusState);
    }

    public BusState viewBusState() {
        return agent.getBusState().toBuilder().build();
    }
}
