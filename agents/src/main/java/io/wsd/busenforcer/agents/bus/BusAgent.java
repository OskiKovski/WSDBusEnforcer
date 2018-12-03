package io.wsd.busenforcer.agents.bus;

import io.wsd.busenforcer.agents.bus.behaviours.ExampleKotlinBehaviour;
import io.wsd.busenforcer.agents.bus.o2a.UpdateLocationO2A;
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

    private Double lat = null;
    private Double lon = null;

    @Override
    public void setup() {
        logger.info("BusAgent started.");
        addBehaviour(new ExampleKotlinBehaviour(this));

        // Object To Agent Communication
        setEnabledO2ACommunication(true, 10);
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                Object obj = getO2AObject();
                if (obj != null) {
                    logger.info("Serving external O2A communication");
                    if (obj instanceof UpdateLocationO2A) {
                        UpdateLocationO2A o2a = (UpdateLocationO2A) obj;
                        lat = o2a.getLat();
                        lon = o2a.getLon();
                        logger.info("Location updated.");
                    } else if (obj instanceof String) {
                        String message = (String) obj;
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
