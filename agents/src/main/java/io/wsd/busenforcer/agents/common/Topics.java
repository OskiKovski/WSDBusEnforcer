package io.wsd.busenforcer.agents.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Topics {
    BUS_STATUS("bus-status"),
    POLICE_STATUS("poplice-status"),
    DANGEROUS_EVENTS("dangerous-events");

    private final String name;

    public String toString() {
        return name;
    }
}
