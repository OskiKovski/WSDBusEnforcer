package io.wsd.busenforcer.agents.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Services {
    POLICE_INTERVENTION_SERVICE("police-service", "police-service");

    final String name;
    final String type;
}
