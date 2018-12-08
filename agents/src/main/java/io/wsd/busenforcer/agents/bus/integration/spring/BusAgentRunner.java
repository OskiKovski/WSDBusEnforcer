package io.wsd.busenforcer.agents.bus.integration.spring;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.integration.spring.SpringAgentRunner;
import io.wsd.busenforcer.agents.common.model.Location;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BusAgentRunner extends SpringAgentRunner<BusAgent> {

    @Value("${agent.bus.number}")
    private String number;

    @Value("${agent.bus.line}")
    private String line;

    @Value("${agent.bus.brigade}")
    private String brigade;

    @Override
    protected String buildAgentNickname() {
        return "bus-" + number + "-agent";
    }

    @Override
    protected BusAgent createAgent() {
        BusState initialBusState = new BusState(line, brigade, new Location(.0, .0));
        return new BusAgent(initialBusState);
    }

    public BusState viewState() {
        return SerializationUtils.clone(agent.getBusState());
    }
}
