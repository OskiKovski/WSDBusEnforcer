package io.wsd.busenforcer.agents.bus;

import io.wsd.busenforcer.agents.bus.behaviours.ExampleKotlinBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusAgent extends Agent {

    public static final String O2A_RAISE_EVENT = "raiseEvent";

    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(BusAgent.class);

    @Override
    public void setup() {
        logger.info("BusAgent started.");
        addBehaviour(new ExampleKotlinBehaviour(this));

        // Object To Agent Communication
        setEnabledO2ACommunication(true, 10);
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                String message = (String) getO2AObject();
                if (message != null) {
                    logger.info("Serving external O2A communication");
                    switch (message) {
                        case O2A_RAISE_EVENT:
                            this.getAgent().addBehaviour(new OneShotBehaviour(this.getAgent()) {
                                @Override
                                public void action() {
                                    logger.info("Raising event on demand");
                                    try {
                                        TopicManagementHelper topicHelper = (TopicManagementHelper)
                                                getHelper(TopicManagementHelper.SERVICE_NAME);
                                        AID topic = topicHelper.createTopic("dangerous-events");
                                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                                        msg.addReceiver(topic);
                                        msg.setContent("Dangerous event");
                                        send(msg);
                                    } catch (ServiceException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
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
