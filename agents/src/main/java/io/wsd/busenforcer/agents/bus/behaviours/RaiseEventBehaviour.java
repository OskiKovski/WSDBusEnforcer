package io.wsd.busenforcer.agents.bus.behaviours;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.common.Services;
import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

import java.io.IOException;
import java.util.Arrays;
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
            ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
            cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
            cfp.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
            cfp.setContentObject(agent.getBusState().getLocation());

            DFAgentDescription agentDescription = new DFAgentDescription();
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType(Services.POLICE_INTERVENTION_SERVICE.getName());
            agentDescription.addServices(serviceDescription);

            Arrays.stream(DFService.search(agent, agentDescription))
                    .map(DFAgentDescription::getName)
                    .forEach(cfp::addReceiver);

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
        } catch (IOException | FIPAException e) {
            // TODO: 09.12.2018 handle exceptions 
            throw new RuntimeException(e);
        }
    }
}
