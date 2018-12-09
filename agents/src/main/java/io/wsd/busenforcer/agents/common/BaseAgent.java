package io.wsd.busenforcer.agents.common;

import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import io.wsd.busenforcer.agents.common.model.AgentModel;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent<T extends AgentModel> extends Agent {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final T model;

    private List<TakeDownAction> takeDownActions = new ArrayList<>();

    public BaseAgent(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void addBehaviour(BehaviourWrapper wrapper) {
        addBehaviour(wrapper.getBehaviour());
    }

    public void addTakeDownAction(TakeDownAction action) {
        takeDownActions.add(action);
    }

    @Override
    public void takeDown() {
        executeTakeDownActions();
    }

    private void executeTakeDownActions() {
        takeDownActions.forEach(TakeDownAction::execute);
    }

    public interface TakeDownAction {
        void execute();
    }
}
