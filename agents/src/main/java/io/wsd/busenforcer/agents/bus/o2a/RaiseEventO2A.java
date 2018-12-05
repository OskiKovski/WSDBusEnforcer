package io.wsd.busenforcer.agents.bus.o2a;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.bus.behaviours.RaiseEventBehaviour;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;

public class RaiseEventO2A implements O2ACommand<BusAgent> {

    @Override
    public void execute(BusAgent agent) {
        agent.addBehaviour(new RaiseEventBehaviour(agent));
    }

    @Override
    public String getName() {
        return "Raise Event";
    }
}
