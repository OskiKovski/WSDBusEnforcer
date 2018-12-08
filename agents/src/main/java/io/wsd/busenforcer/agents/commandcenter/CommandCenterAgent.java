package io.wsd.busenforcer.agents.commandcenter;

import io.wsd.busenforcer.agents.commandcenter.behaviours.BusStatusTopicListenerBehaviour;
import io.wsd.busenforcer.agents.commandcenter.behaviours.PoliceStatusTopicListenerBehaviour;
import io.wsd.busenforcer.agents.commandcenter.model.CommandCenterState;
import io.wsd.busenforcer.agents.common.BaseAgent;

public class CommandCenterAgent extends BaseAgent<CommandCenterState> {
    static final long serialVersionUID = 1L;

    public CommandCenterAgent() {
        this(new CommandCenterState());
    }

    public CommandCenterAgent(CommandCenterState model) {
        super(model);
    }

    @Override
    public void setup() {
        log.info("Started.");
        addBehaviour(new BusStatusTopicListenerBehaviour(this));
        addBehaviour(new PoliceStatusTopicListenerBehaviour(this));
    }

    @Override
    public void takeDown() {

    }

}
