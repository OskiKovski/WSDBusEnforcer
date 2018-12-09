package io.wsd.busenforcer.agents.police;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import io.wsd.busenforcer.agents.common.BaseAgent;
import io.wsd.busenforcer.agents.common.Services;
import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.behaviours.PublishPoliceStatusBehaviour;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetResponder;

public class PoliceAgent extends BaseAgent<PoliceState> {
    static final long serialVersionUID = 1L;

    private InterventionEvaluator interventionEvaluator;

    public PoliceAgent() {
        this(PoliceState.empty());
    }

    public PoliceAgent(PoliceState model) {
        super(model);
        registerInterventionEvaluator(eventLocation -> {
            double distance = LatLngTool.distance(this.getModel().getLocation().toLatLng(), eventLocation.toLatLng(),
                    LengthUnit.KILOMETER);
            return 1 / distance;
        });
    }

    @Override
    public void setup() {
        Object[] arguments = getArguments();
        if (arguments != null && arguments.length == 3) {
            String id = (String) arguments[0];
            double lat = Double.parseDouble((String) arguments[1]);
            double lon = Double.parseDouble((String) arguments[2]);
            Location location = Location.of(lat, lon);
            this.model.setUnitId(id);
            this.model.setLocation(location);
        }
        registerServices();
        addBehaviour(new PublishPoliceStatusBehaviour(this));
        addBehaviour(new BehaviourWrapper<PoliceAgent>(this) {

            @Override
            protected Behaviour createBehaviour() {
                MessageTemplate messageTemplate = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.CFP),
                        MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET));
                return new ContractNetResponder(agent, messageTemplate) {

                    @Override
                    protected ACLMessage handleCfp(ACLMessage cfp) throws RefuseException, FailureException, NotUnderstoodException {
                        log.info("Recieved dangerous event intervention request.");
                        if(!agent.getModel().isAvailable()) {
                            ACLMessage refuse = cfp.createReply();
                            refuse.setPerformative(ACLMessage.REFUSE);
                            refuse.setContent("Not available, refusing intervention request.");
                            throw new RefuseException(refuse);
                        }
                        Location location = null;
                        try {
                            location = (Location) cfp.getContentObject();
                        } catch (ClassCastException | UnreadableException e) {
                            // TODO: 08.12.2018 handle by throwing not understood probably
                            e.printStackTrace();
                        }
                        Double score = interventionEvaluator.evaluate(location);
                        ACLMessage propose = cfp.createReply();
                        propose.setPerformative(ACLMessage.PROPOSE);
                        propose.setContent(String.valueOf(score));
                        return propose;
                    }

                    @Override
                    protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
                        log.info("Chosen for intervention. Sending confirmation.");
                        agent.getModel().setAvailable(false);
                        ACLMessage inform = accept.createReply();
                        // TODO: 09.12.2018 this should take input from GUI somehow
                        inform.setPerformative(ACLMessage.INFORM);
                        return inform;
                    }

                    @Override
                    protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
                        log.info("Not chosen for intervention. Standing by for next events.");
                    }
                };
            }
        });
        log.info("Started.");
    }


    private void registerServices() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Services.POLICE_INTERVENTION_SERVICE.getType());
        serviceDescription.setName(Services.POLICE_INTERVENTION_SERVICE.getName());
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        dfAgentDescription.addServices(serviceDescription);
        try {
            DFService.register(this, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enables changing of default agent intervention evaluator.
     *
     * @param interventionEvaluator
     */
    public void registerInterventionEvaluator(InterventionEvaluator interventionEvaluator) {
        this.interventionEvaluator = interventionEvaluator;
    }

    @Override
    public void takeDown() {
        super.takeDown();
        deregisterServices();
    }

    private void deregisterServices() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            // TODO: 09.12.2018 handle exception
            log.error("Coulnd't deregister servicese", e);
        }
    }

    @FunctionalInterface
    public interface InterventionEvaluator {
        /**
         * Should return intervention score - the higher the more suited agent is for intervention.
         *
         * @param location event data.
         * @return intervention score.
         */
        Double evaluate(Location location);
    }
}
