package io.wsd.busenforcer.agents.policecar;

import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoliceCarAgent extends Agent {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(PoliceCarAgent.class);

    @Override
    public void setup() {
        logger.info("PoliceCarAgent started.");
    }

    @Override
    public void takeDown() {
    }
}
