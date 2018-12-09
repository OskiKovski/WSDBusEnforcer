package io.wsd.busenforcer.agents.bus.behaviours;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.common.Topics;
import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class RaiseEventBehaviour extends BehaviourWrapper<BusAgent> {

    public RaiseEventBehaviour(BusAgent agent) {
        super(agent);
    }

    @Override
    protected Behaviour createBehaviour() {
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper)
                    agent.getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topicAID = topicHelper.createTopic(Topics.DANGEROUS_EVENTS.getName());

            ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
            cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
            cfp.addReceiver(topicAID);
            cfp.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
            cfp.setContentObject(agent.getBusState().getLocation());

            return new ContractNetInitiator(agent, cfp) {

                @Override
                public void onStart() {
                    super.onStart();
                    log.info("Raising dangerous event.");
                }

                @Override
                protected void handlePropose(ACLMessage propose, Vector acceptances) {
                    log.info("Propose: " + propose);
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void handleAllResponses(Vector responses, Vector acceptances) {
                    log.info("Handling proposals.");
                    List<ACLMessage> proposals = ((Vector<ACLMessage>) responses).stream()
                            .filter(response -> response.getPerformative() == ACLMessage.PROPOSE)
                            .collect(Collectors.toList());
                    if (!proposals.isEmpty()) {
                        ACLMessage bestProposal = proposals.stream()
                                .max(Comparator.comparing(p -> Double.parseDouble(p.getContent()))).get();

                        ACLMessage accept = bestProposal.createReply();
                        accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                        acceptances.add(accept);

                        List<ACLMessage> rejects = proposals.stream()
                                .filter(p -> !p.equals(bestProposal))
                                .map(ACLMessage::createReply)
                                .collect(Collectors.toList());
                        rejects.forEach(r -> r.setPerformative(ACLMessage.REJECT_PROPOSAL));
                        acceptances.addAll(rejects);
                        log.info("Accepted proposal: " + bestProposal);
                    } else {
                        log.info("No proposals.");
                    }
                }

                protected void handleInform(ACLMessage inform) {
                    log.info("Inform: " + inform);
                }
            };
        } catch (IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
