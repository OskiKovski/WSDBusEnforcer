package io.wsd.busenforcer.agents.police;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import io.wsd.busenforcer.agents.common.BaseAgent;
import io.wsd.busenforcer.agents.common.Topics;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.behaviours.PublishPoliceStatusBehaviour;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import jade.core.AID;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetResponder;

public class PoliceAgent extends BaseAgent<PoliceState> {
    static final long serialVersionUID = 1L;

    private InterventionEvaluator interventionEvaluator;

    public PoliceAgent() {
        this(new PoliceState());
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
        if (arguments.length == 3) {
            String id = (String) arguments[0];
            double lat = Double.parseDouble((String) arguments[1]);
            double lon = Double.parseDouble((String) arguments[2]);
            Location location = new Location(lat, lon);
            this.model.setId(id);
            this.model.setLocation(location);
        }
        log.info("Started.");
        addBehaviour(new PublishPoliceStatusBehaviour(this));
        try {
            TopicManagementHelper topicHelper = (TopicManagementHelper)
                    getHelper(TopicManagementHelper.SERVICE_NAME);
            AID topicAID = topicHelper.createTopic(Topics.DANGEROUS_EVENTS.getName());
            topicHelper.register(topicAID);
            MessageTemplate messageTemplate = MessageTemplate.MatchTopic(topicAID);
            addBehaviour(new ContractNetResponder(this, messageTemplate) {

                @Override
                protected ACLMessage handleCfp(ACLMessage cfp) throws RefuseException, FailureException, NotUnderstoodException {
                    log.info("Recieved Cfp " + cfp);
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
                    log.info("Proposal accepted: " + accept);
                    return null;
                }

                @Override
                protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
                    log.info("Proposal rejected: " + reject);
                }
            });
        } catch (ServiceException e) {
            log.error("Failed to register on topic \"" + Topics.DANGEROUS_EVENTS + "\".", e);
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

    @FunctionalInterface
    interface InterventionEvaluator {
        /**
         * Should return intervention score - the higher the more suited agent is for intervention.
         *
         * @param location1 event data.
         * @return intervention score.
         */
        Double evaluate(Location location1);
    }
}
