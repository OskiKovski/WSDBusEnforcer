package io.wsd.busenforcer.agents.bus;

import io.wsd.busenforcer.agents.bus.behaviours.PublishBusStatusBehaviour;
import io.wsd.busenforcer.agents.common.BaseAgent;
import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.common.o2a.O2ABehaviour;
import jade.core.Agent;

public class BusAgent extends BaseAgent<BusState> {

    static final long serialVersionUID = 1L;

    public BusAgent(BusState model) {
        super(model);
    }

    @Override
    public void setup() {
        logger.info("BusAgent started.");
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
