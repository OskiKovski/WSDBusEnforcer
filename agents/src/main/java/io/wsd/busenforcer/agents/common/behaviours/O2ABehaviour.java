package io.wsd.busenforcer.agents.common.behaviours;

import io.wsd.busenforcer.agents.common.o2a.O2ACommand;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            @SuppressWarnings("unchecked")
            O2ACommand<T> command = (O2ACommand<T>) obj;
            logger.debug("O2A command received.");
            command.execute(agent);
            logger.debug("O2A command execution finished.");
        } else {
            block();
        }
    }
}
