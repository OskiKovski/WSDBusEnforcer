package io.wsd.busenforcer.agents.common.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BehaviourWrapper<T extends Agent> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final T agent;

    private final Behaviour behaviour;

    public BehaviourWrapper(T agent) {
        this.agent = agent;
        this.behaviour = createBehaviour();
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    protected abstract Behaviour createBehaviour();


}
