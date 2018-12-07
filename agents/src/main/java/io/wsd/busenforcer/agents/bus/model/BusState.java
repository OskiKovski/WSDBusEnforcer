package io.wsd.busenforcer.agents.bus.model;

import io.wsd.busenforcer.agents.common.model.AgentModel;
import io.wsd.busenforcer.agents.common.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class BusState implements AgentModel, Serializable {
    String line;
    String brigade;
    Location location;
}
