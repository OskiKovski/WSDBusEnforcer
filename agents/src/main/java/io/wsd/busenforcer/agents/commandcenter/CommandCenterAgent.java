package io.wsd.busenforcer.agents.commandcenter;

import io.wsd.busenforcer.agents.commandcenter.behaviours.BusStatusTopicListenerBehaviour;
import io.wsd.busenforcer.agents.commandcenter.behaviours.PoliceStatusTopicListenerBehaviour;
import io.wsd.busenforcer.agents.commandcenter.model.CommandCenterState;
import io.wsd.busenforcer.agents.common.BaseAgent;

public class CommandCenterAgent extends BaseAgent<CommandCenterState> {
    static final long serialVersionUID = 1L;

    public CommandCenterAgent() {
        super(CommandCenterState.empty());
    }

    @Override
    public void setup() {
        log.info("Started.");
        BusStatusTopicListenerBehaviour busStatusListener = new BusStatusTopicListenerBehaviour(this);
        addBehaviour(busStatusListener);
        addTakeDownAction(busStatusListener::unregisterTopic);
        PoliceStatusTopicListenerBehaviour policeStatusListener = new PoliceStatusTopicListenerBehaviour(this);
        addBehaviour(policeStatusListener);
        addTakeDownAction(policeStatusListener::unregisterTopic);
    }

}
