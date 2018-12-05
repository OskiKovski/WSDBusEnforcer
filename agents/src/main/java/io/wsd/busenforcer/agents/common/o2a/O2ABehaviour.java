package io.wsd.busenforcer.agents.common.o2a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class O2ABehaviour<T extends Agent> extends CyclicBehaviour {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(O2ABehaviour.class);

    private final T agent;
    
    public O2ABehaviour(T agent) {
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        Object obj = agent.getO2AObject();
        if (obj instanceof O2ACommand) {
            O2ACommand<T> command = (O2ACommand<T>) obj;
            logger.info("O2A command received.");
            command.execute(agent);
            logger.info("Command execution finished.");
        } else {
            block();
        }
    }
}
