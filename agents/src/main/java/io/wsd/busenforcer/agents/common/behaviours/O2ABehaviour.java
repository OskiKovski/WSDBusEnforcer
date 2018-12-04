package io.wsd.busenforcer.agents.common.behaviours;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wsd.busenforcer.agents.bus.o2a.UpdateLocationO2A;
import io.wsd.busenforcer.agents.common.o2a.O2AAction;
import io.wsd.busenforcer.agents.common.o2a.O2AMessage;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;

public class O2ABehaviour extends CyclicBehaviour {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(O2ABehaviour.class);
    
    private Map<Class<? extends O2AMessage>, O2AAction> actionMap = new HashMap<>();
    
    public O2ABehaviour(Agent a) {
        super(a);
    }

    @Override
    public void action() {
        Object obj = myAgent.getO2AObject();
        if (obj != null && obj instanceof O2AMessage) {
            logger.info("Serving external O2A communication");
            O2AMessage message = (O2AMessage) obj;
            O2AAction action = actionMap.get(message.getClass());
            action.execute();
            
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
