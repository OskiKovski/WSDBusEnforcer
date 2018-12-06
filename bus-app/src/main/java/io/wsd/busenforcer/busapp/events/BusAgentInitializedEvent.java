package io.wsd.busenforcer.busapp.events;

import org.springframework.context.ApplicationEvent;

public class BusAgentInitializedEvent extends ApplicationEvent {

    public BusAgentInitializedEvent(Object source) {
        super(source);
    }
}
