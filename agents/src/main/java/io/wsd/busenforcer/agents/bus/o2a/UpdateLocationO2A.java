package io.wsd.busenforcer.agents.bus.o2a;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;

public class UpdateLocationO2A implements O2ACommand<BusAgent> {
    
    private final Location location;

    public UpdateLocationO2A(Location location) {
        this.location = location;
    }

    @Override
    public void execute(BusAgent agent) {
        agent.setLocation(location);
    }

    @Override
    public String getName() {
        return "Update Location";
    }
}