package io.wsd.busenforcer.agents.police.behaviours;

import io.wsd.busenforcer.agents.common.Topics;
import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import io.wsd.busenforcer.agents.police.PoliceAgent;
import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

// TODO: 08.12.2018 jkumor clone of PublishBusStatusBehaviour
public class PublishPoliceStatusBehaviour extends BehaviourWrapper<PoliceAgent> {

    public PublishPoliceStatusBehaviour(PoliceAgent agent) {
        super(agent);
    }

    @Override
    protected Behaviour createBehaviour() {
        // TODO: 07.12.2018 set reasonable period
        return new TickerBehaviour(agent, 5000) {

            private AID topic;

            @Override
            public void onStart() {
                super.onStart();
                try {
                    Topics topic = Topics.POLICE_STATUS;
                    TopicManagementHelper topicHelper = (TopicManagementHelper)
                            agent.getHelper(TopicManagementHelper.SERVICE_NAME);
                    this.topic = topicHelper.createTopic(topic.getName());
                } catch (ServiceException e) {
                    logger.error("Can't create topic: " + topic.getName());
                }
            }

            @Override
            protected void onTick() {
                try {
                    logger.info("Publishing status.");
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(topic);
                    msg.setContentObject(agent.getModel());
                    agent.send(msg);
                } catch (IOException e) {
                    logger.error("Error during status publication.", e);
                }
            }
        };
    }
}
