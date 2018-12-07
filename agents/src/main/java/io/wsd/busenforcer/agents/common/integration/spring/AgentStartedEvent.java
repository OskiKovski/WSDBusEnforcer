package io.wsd.busenforcer.agents.common.integration.spring;

import org.springframework.context.ApplicationEvent;

public class AgentStartedEvent extends ApplicationEvent {

    public AgentStartedEvent(Object source) {
        super(source);
    }
}
