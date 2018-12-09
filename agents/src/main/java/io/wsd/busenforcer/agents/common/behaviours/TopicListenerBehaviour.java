package io.wsd.busenforcer.agents.common.behaviours;

import io.wsd.busenforcer.agents.common.Topics;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TopicListenerBehaviour extends SequentialBehaviour {

    private final Topics topic;
    private MessageTemplate template = null;

    public TopicListenerBehaviour(Agent agent, final Topics topic) {
        super(agent);
        this.topic = topic;
        addTopicRegisterSubBehaviour();
        addTopicReadSubBehaviour(topic);
    }

    private void addTopicRegisterSubBehaviour() {
        addSubBehaviour(new OneShotBehaviour(myAgent) {
            @Override
            public void action() {
                try {
                    TopicManagementHelper topicHelper = (TopicManagementHelper)
                            myAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
                    AID topicAID = topicHelper.createTopic(topic.getName());
                    topicHelper.register(topicAID);
                    template = MessageTemplate.MatchTopic(topicAID);
                } catch (ServiceException e) {
                    log.error("Failed to register on topic \"" + topic + "\".", e);
                }
            }
        });
    }

    private void addTopicReadSubBehaviour(Topics topic) {
        addSubBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                if (template != null) {
                    ACLMessage msg = myAgent.receive(template);
                    if (msg != null) {
                        log.debug("Received message from topic \"" + topic + "\"");
                        handleMessage(msg);
                    } else {
                        block();
                    }
                }
            }

            @Override
            public boolean done() {
                return template == null;
            }
        });
    }

    public void unregisterTopic() {
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper)
                    myAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topicAID = topicHelper.createTopic(topic.getName());
            topicHelper.register(topicAID);
        } catch (ServiceException e) {
            log.error("Failed to unregister on topic \"" + topic + "\".", e);
        }
    }

    protected abstract void handleMessage(ACLMessage msg);
}
