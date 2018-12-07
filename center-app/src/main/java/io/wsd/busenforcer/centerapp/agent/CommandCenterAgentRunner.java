package io.wsd.busenforcer.centerapp.agent;

import io.wsd.busenforcer.agents.commandcenter.CommandCenterAgent;
import io.wsd.busenforcer.agents.commandcenter.model.CommandCenterState;
import io.wsd.busenforcer.agents.common.integration.spring.SpringAgentRunner;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Component;

@Component
public class CommandCenterAgentRunner extends SpringAgentRunner<CommandCenterAgent> {

    @Override
    protected String buildAgentNickname() {
        return "command-center-agent";
    }

    @Override
    protected CommandCenterAgent createAgent() {
        return new CommandCenterAgent(new CommandCenterState());
    }

    public CommandCenterState viewState() {
        return SerializationUtils.clone(agent.getModel());
    }
}
