package io.wsd.busenforcer.agents.bus.model;

import io.wsd.busenforcer.agents.common.model.AgentModel;
import io.wsd.busenforcer.agents.common.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusState implements AgentModel {
    private String line;
    private String brigade;
    private Location location;
}
