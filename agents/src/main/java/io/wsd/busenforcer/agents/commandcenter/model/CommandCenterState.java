package io.wsd.busenforcer.agents.commandcenter.model;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.commandcenter.CommandCenterAgent;
import io.wsd.busenforcer.agents.common.model.AgentModel;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
@NoArgsConstructor
public class CommandCenterState implements AgentModel {
    final Map<String, BusState> busStates = new HashMap<>();
}
