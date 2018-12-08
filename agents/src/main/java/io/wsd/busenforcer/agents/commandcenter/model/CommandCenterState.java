package io.wsd.busenforcer.agents.commandcenter.model;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.model.AgentModel;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Getter
@NoArgsConstructor
public class CommandCenterState implements AgentModel {
    final Map<String, BusState> busStates = new HashMap<>();
    final Map<String, PoliceState> policeStates = new HashMap<>();
}
