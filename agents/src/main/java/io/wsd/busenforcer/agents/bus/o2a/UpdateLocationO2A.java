package io.wsd.busenforcer.agents.bus.o2a;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.bus.behaviours.PublishBusStatusBehaviour;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateLocationO2A implements O2ACommand<BusAgent> {

    private final Location location;

    @Override
    public void execute(BusAgent agent) {
        agent.getBusState().setLocation(location);
    }

    @Override
    public String getName() {
        return "Update Location";
    }
}
