package io.wsd.busenforcer.agents.commandcenter.behaviours;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.commandcenter.CommandCenterAgent;
import io.wsd.busenforcer.agents.common.Topics;
import io.wsd.busenforcer.agents.common.behaviours.TopicListenerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class BusStatusTopicListenerBehaviour extends TopicListenerBehaviour {

    private CommandCenterAgent commandCenterAgent;

    public BusStatusTopicListenerBehaviour(CommandCenterAgent commandCenterAgent) {
        super(commandCenterAgent, Topics.BUS_STATUS);
        this.commandCenterAgent = commandCenterAgent;
    }

    @Override
    protected void handleMessage(ACLMessage msg) {
        try {
            Serializable contentObject = msg.getContentObject();
            if (contentObject instanceof BusState) {
                log.info("Bus status recieved " + contentObject);
                commandCenterAgent.getModel().getBusStates().put(msg.getSender().getName(), (BusState) contentObject);
            }
        } catch (UnreadableException e) {
            log.error("Error reading bus status: ", e);
        }
    }
}
