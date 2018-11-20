package io.wsd.busenforcer.agents.bus;

import io.wsd.busenforcer.agents.bus.behaviours.ExampleBehaviour;
import io.wsd.busenforcer.agents.bus.behaviours.ExampleKotlinBehaviour;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusAgent extends Agent {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(BusAgent.class);

    @Override
    public void setup() {
        logger.info("BusAgent started.");
        //final String otherAgentName = (String) this.getArguments()[0];
        addBehaviour(new ExampleBehaviour(this));
        addBehaviour(new ExampleKotlinBehaviour(this));
    }

    @Override
    public void takeDown() {
    }
}
