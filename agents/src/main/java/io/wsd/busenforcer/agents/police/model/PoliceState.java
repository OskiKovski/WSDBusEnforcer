package io.wsd.busenforcer.agents.police.model;

import io.wsd.busenforcer.agents.common.model.AgentModel;
import io.wsd.busenforcer.agents.common.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "empty")
public class PoliceState implements AgentModel {
    private String unitId = "";
    private Location location = Location.zero();
    private boolean available = true;
}
