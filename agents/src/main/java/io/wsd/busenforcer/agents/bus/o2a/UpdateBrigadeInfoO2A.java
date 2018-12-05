package io.wsd.busenforcer.agents.bus.o2a;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateBrigadeInfoO2A implements O2ACommand<BusAgent> {


    private final String line;

    private final String brigade;

    @Override
    public void execute(BusAgent agent) {
        agent.getBusState().setLine(line);
        agent.getBusState().setBrigade(brigade);
    }

    @Override
    public String getName() {
        return "Update Line and Brigade Information";
    }
}
