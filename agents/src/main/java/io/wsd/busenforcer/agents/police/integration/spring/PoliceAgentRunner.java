package io.wsd.busenforcer.agents.police.integration.spring;

import io.wsd.busenforcer.agents.common.integration.spring.SpringAgentRunner;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.PoliceAgent;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PoliceAgentRunner extends SpringAgentRunner<PoliceAgent> {

    @Value("${agent.police.id}")
    private String id;

    @Value("${agent.police.lat}")
    private Double lat;

    @Value("${agent.police.lon}")
    private Double lon;

    @Override
    protected String buildAgentNickname() {
        return "police-" + id + "-agent";
    }

    @Override
    protected PoliceAgent createAgent() {
        PoliceState initialPoliceState = new PoliceState(id, new Location(lat, lon), false);
        return new PoliceAgent(initialPoliceState);
    }

    public PoliceState viewState() {
        return SerializationUtils.clone(agent.getModel());
    }
}
