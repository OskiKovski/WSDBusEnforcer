package io.wsd.busenforcer.agents.commandcenter;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.commandcenter.model.CommandCenterState;
import io.wsd.busenforcer.agents.common.BaseAgent;
import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.Serializable;

public class CommandCenterAgent extends BaseAgent<CommandCenterState> {
    static final long serialVersionUID = 1L;

    public CommandCenterAgent(CommandCenterState model) {
        super(model);
    }

    @Override
    public void setup() {
        logger.info("Started.");
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper)
                    getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topic = topicHelper.createTopic("bus-status");
            topicHelper.register(topic);
            final MessageTemplate template = MessageTemplate.MatchTopic(topic);
            addBehaviour(new CyclicBehaviour(this) {
                public void action() {
                    ACLMessage msg = myAgent.receive(template);
                    if (msg != null) {
                        try {
                            Serializable contentObject = msg.getContentObject();
                            logger.info("Recieved " + contentObject);
                            if(contentObject instanceof BusState) {
                                model.getBusStates().put(msg.getSender().getName(), (BusState) contentObject);
                            }
                        } catch (UnreadableException e) {
                            logger.error("Error reading bus status: ", e);
                        }
                    } else {
                        block();
                    }
                }
            });
        } catch (ServiceException e) {
            logger.error("Error: ", e);
        }
    }

    @Override
    public void takeDown() {
    }
}
