package io.wsd.busenforcer.agents.bus;

import io.wsd.busenforcer.agents.bus.behaviours.ExampleBehaviour;
import io.wsd.busenforcer.agents.bus.behaviours.ExampleKotlinBehaviour;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusAgent extends Agent {

    public static final String O2A_RAISE_EVENT = "raiseEvent";

    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(BusAgent.class);

    @Override
    public void setup() {
        logger.info("BusAgent started.");
        addBehaviour(new ExampleBehaviour(this));
        addBehaviour(new ExampleKotlinBehaviour(this));

        // Object To Agent Communication
        setEnabledO2ACommunication(true, 10);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                String message = (String) getO2AObject();
                if (message != null) {
                    logger.info("Serving external O2A communication");
                    switch (message) {
                        case O2A_RAISE_EVENT:
                            logger.info("Raising event on demand");
                            break;

                        default:
                            logger.error("Unknown O2A message");
                    }
                } else {
                    block();
                }
            }
        });

    }

    @Override
    public void takeDown() {
        setEnabledO2ACommunication(false, 0);
    }
}
