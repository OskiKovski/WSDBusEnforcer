package io.wsd.busenforcer.agents.police.integration.spring;

import io.wsd.busenforcer.agents.common.integration.spring.SpringAgentRunner;
import io.wsd.busenforcer.agents.police.PoliceAgent;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PoliceAgentRunner extends SpringAgentRunner<PoliceAgent> {

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
    protected PoliceAgent createAgent() {
        return new PoliceAgent(new PoliceState());
    }

    public PoliceState viewState() {
        return SerializationUtils.clone(agent.getModel());
    }
}
