package io.wsd.busenforcer.agents.commandcenter.behaviours;

import io.wsd.busenforcer.agents.commandcenter.CommandCenterAgent;
import io.wsd.busenforcer.agents.common.Topics;
import io.wsd.busenforcer.agents.common.behaviours.TopicListenerBehaviour;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class PoliceStatusTopicListenerBehaviour extends TopicListenerBehaviour {

    private CommandCenterAgent commandCenterAgent;

    public PoliceStatusTopicListenerBehaviour(CommandCenterAgent commandCenterAgent) {
        super(commandCenterAgent, Topics.POLICE_STATUS);
        this.commandCenterAgent = commandCenterAgent;
    }

    @Override
    protected void handleMessage(ACLMessage msg) {
        try {
            Serializable contentObject = msg.getContentObject();
            if (contentObject instanceof PoliceState) {
                log.info("Police status recieved " + contentObject);
                commandCenterAgent.getModel().getPoliceStates().put(msg.getSender().getName(),
                        (PoliceState) contentObject);
            }
        } catch (UnreadableException e) {
            log.error("Error reading police status: ", e);
        }
    }
}
