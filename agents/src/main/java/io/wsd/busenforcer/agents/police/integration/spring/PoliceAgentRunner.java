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

    @Value("${agent.police.unitId}")
    private String unitId;

    @Override
    protected String buildAgentNickname() {
        return "police-unit-" + unitId + "-agent";
    }

    @Override
    protected PoliceAgent createAgent() {
        return new PoliceAgent(PoliceState.of(unitId, Location.zero(), true));
    }

    public void registerInterventionEvaluator(PoliceAgent.InterventionEvaluator interventionEvaluator) {
        agent.registerInterventionEvaluator(interventionEvaluator);
    }

    public PoliceState viewState() {
        return SerializationUtils.clone(agent.getModel());
    }

    public void updateLocation(Location location) {
        synchronized (lock) {
            agent.getModel().setLocation(location);
        }
    }
}
