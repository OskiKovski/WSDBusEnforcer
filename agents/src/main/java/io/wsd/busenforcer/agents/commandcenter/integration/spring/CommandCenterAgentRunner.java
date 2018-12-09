package io.wsd.busenforcer.agents.commandcenter.integration.spring;

import io.wsd.busenforcer.agents.commandcenter.CommandCenterAgent;
import io.wsd.busenforcer.agents.commandcenter.model.CommandCenterState;
import io.wsd.busenforcer.agents.common.integration.spring.SpringAgentRunner;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandCenterAgentRunner extends SpringAgentRunner<CommandCenterAgent> {

    @Override
    protected String buildAgentNickname() {
        return "command-center-agent";
    }

    @Override
    protected CommandCenterAgent createAgent() {
        return new CommandCenterAgent();
    }

    public Optional<CommandCenterState> viewState() {
        return Optional.ofNullable(agent)
                .map(a -> SerializationUtils.clone(agent.getModel()));
    }
}
