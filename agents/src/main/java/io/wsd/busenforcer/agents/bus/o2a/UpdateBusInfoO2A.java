package io.wsd.busenforcer.agents.bus.o2a;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.bus.model.BusInfo;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ACommand;

public class UpdateBusInfoO2A implements O2ACommand<BusAgent> {


    private final BusInfo busInfo;

    public UpdateBusInfoO2A(BusInfo busInfo) {
        this.busInfo = busInfo;
    }

    @Override
    public void execute(BusAgent agent) {
        agent.setBusInfo(busInfo);
    }

    @Override
    public String getName() {
        return "Update Bus Information";
    }
}
