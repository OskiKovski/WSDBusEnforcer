package io.wsd.busenforcer.agents.bus.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class ExampleBehaviour extends Behaviour {
    static final long serialVersionUID = 1L;

    public ExampleBehaviour(Agent a) {
        super(a);
    }

    @Override
    public void action() {
        System.out.println("Example behaviour");
    }

    @Override
    public boolean done() {
        return true;
    }
}
