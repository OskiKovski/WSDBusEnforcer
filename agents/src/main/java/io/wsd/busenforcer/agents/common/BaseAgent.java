package io.wsd.busenforcer.agents.common;

import io.wsd.busenforcer.agents.common.behaviours.BehaviourWrapper;
import io.wsd.busenforcer.agents.common.model.AgentModel;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAgent<T extends AgentModel> extends Agent {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final T model;

    public BaseAgent(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void addBehaviour(BehaviourWrapper wrapper) {
        addBehaviour(wrapper.getBehaviour());
    }
}
