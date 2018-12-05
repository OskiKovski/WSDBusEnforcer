package io.wsd.busenforcer.agents.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ABehaviour;
import jade.core.Agent;

public class BusAgent extends Agent {

    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(BusAgent.class);

    private BusState busState;

    public BusAgent(BusState busState) {
        this.busState = busState;
    }

    @Override
    public void setup() {
        logger.info("BusAgent started.");
        setEnabledO2ACommunication(true, 10);
        addBehaviour(new O2ABehaviour<>(this));
    }

    @Override
    public void takeDown() {
        setEnabledO2ACommunication(false, 0);
    }

    public BusState getBusState() {
        return busState;
    }
}
