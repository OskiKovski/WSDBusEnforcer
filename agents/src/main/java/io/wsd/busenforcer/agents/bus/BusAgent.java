package io.wsd.busenforcer.agents.bus;

import io.wsd.busenforcer.agents.bus.behaviours.PublishBusStatusBehaviour;
import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.BaseAgent;
import io.wsd.busenforcer.agents.common.behaviours.O2ABehaviour;

public class BusAgent extends BaseAgent<BusState> {

    static final long serialVersionUID = 1L;

    public BusAgent() {
        this(new BusState());
    }
    public BusAgent(BusState model) {
        super(model);
    }

    @Override
    public void setup() {
        log.info("BusAgent started.");
        setEnabledO2ACommunication(true, 10);
        addBehaviour(new O2ABehaviour<>(this));
        addBehaviour(new PublishBusStatusBehaviour(this));
    }

    @Override
    public void takeDown() {
        setEnabledO2ACommunication(false, 0);
    }

    public BusState getBusState() {
        return model;
    }
}
