package io.wsd.busenforcer.agents.commandcenter;

import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandCenterAgent extends Agent {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(CommandCenterAgent.class);

    @Override
    public void setup() {
        logger.info("CommandCenterAgent started.");
    }

    @Override
    public void takeDown() {
    }
}
