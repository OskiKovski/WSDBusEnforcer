package io.wsd.busenforcer.agents.policecar;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoliceCarAgent extends Agent {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(PoliceCarAgent.class);

    @Override
    public void setup() {
        logger.info("PoliceCarAgent started.");
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper)
                    getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topic = topicHelper.createTopic("dangerous-events");
            topicHelper.register(topic);
            final MessageTemplate tpl = MessageTemplate.MatchTopic(topic);
            addBehaviour(new CyclicBehaviour(this) {
                public void action() {
                    ACLMessage msg = myAgent.receive(tpl);
                    if (msg != null) {
                        logger.info("Received event info: " + msg.getContent());
                    } else {
                        block();
                    }
                }
            });
        } catch (
                ServiceException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void takeDown() {
    }
}
