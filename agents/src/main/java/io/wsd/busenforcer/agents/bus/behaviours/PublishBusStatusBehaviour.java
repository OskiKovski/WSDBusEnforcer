package io.wsd.busenforcer.agents.bus.behaviours;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class PublishBusStatusBehaviour extends BehaviourWrapper<BusAgent> {

    public PublishBusStatusBehaviour(BusAgent agent) {
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
                String topicName = "bus-status";
                try {
                    TopicManagementHelper topicHelper = (TopicManagementHelper)
                            agent.getHelper(TopicManagementHelper.SERVICE_NAME);
                    topic = topicHelper.createTopic(topicName);
                } catch (ServiceException e) {
                    logger.error("Can't create topic: " + topicName);
                }
            }

            @Override
            protected void onTick() {
                try {
                    logger.info("Publishing status.");
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(topic);
                    msg.setContentObject(agent.getBusState());
                    agent.send(msg);
                } catch (IOException e) {
                    logger.error("Error during status publication.", e);
                }
            }
        };
    }
}
