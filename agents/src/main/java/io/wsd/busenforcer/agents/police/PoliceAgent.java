package io.wsd.busenforcer.agents.police;

import io.wsd.busenforcer.agents.common.BaseAgent;
import io.wsd.busenforcer.agents.common.Topics;
import io.wsd.busenforcer.agents.common.behaviours.TopicListenerBehaviour;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.behaviours.PublishPoliceStatusBehaviour;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import jade.lang.acl.ACLMessage;

public class PoliceAgent extends BaseAgent<PoliceState> {
    static final long serialVersionUID = 1L;

    public PoliceAgent() {
        this(new PoliceState());
    }

    public PoliceAgent(PoliceState model) {
        super(model);
    }

    @Override
    public void setup() {
        Object[] arguments = getArguments();
        if (arguments != null && arguments.length == 3) {
            String id = (String) arguments[0];
            double lat = Double.parseDouble((String) arguments[1]);
            double lon = Double.parseDouble((String) arguments[2]);
            Location location = new Location(lat, lon);
            this.model.setId(id);
            this.model.setLocation(location);
        }
        log.info("Started.");
        addBehaviour(new TopicListenerBehaviour(this, Topics.DANGEROUS_EVENTS) {
            @Override
            protected void handleMessage(ACLMessage msg) {
                log.info("Received event info: " + msg.getContent());
            }
        });
        addBehaviour(new PublishPoliceStatusBehaviour(this));
    }

    @Override
    public void takeDown() {
    }
}
