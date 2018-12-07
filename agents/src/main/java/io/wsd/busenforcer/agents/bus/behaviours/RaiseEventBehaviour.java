package io.wsd.busenforcer.agents.bus.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wsd.busenforcer.agents.bus.BusAgent;
import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;

public class RaiseEventBehaviour extends OneShotBehaviour {

    private final Logger logger = LoggerFactory.getLogger(RaiseEventBehaviour.class);

    private final BusAgent busAgent;

    public RaiseEventBehaviour(BusAgent busAgent) {
        super(busAgent);
        this.busAgent = busAgent;
    }

    @Override
    public void action() {
        try {
            logger.info("Raising dangerous event.");
            TopicManagementHelper topicHelper = (TopicManagementHelper)
                    busAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topic = topicHelper.createTopic("dangerous-events");
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(topic);
            msg.setContent("Dangerous event at: " + busAgent.getBusState().getLocation());
            busAgent.send(msg);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
