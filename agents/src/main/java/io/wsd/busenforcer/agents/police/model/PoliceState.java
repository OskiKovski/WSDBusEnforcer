package io.wsd.busenforcer.agents.police.model;

import io.wsd.busenforcer.agents.common.model.AgentModel;
import io.wsd.busenforcer.agents.common.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliceState implements AgentModel {
    private String id;
    private Location location;
    private boolean available = true;
}
